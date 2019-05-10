package com.dimple.service.impl;

import com.dimple.dao.ExamQuestionDao;
import com.dimple.dao.ExamRecordDao;
import com.dimple.dao.QuestionDao;
import com.dimple.entity.ExamQuestion;
import com.dimple.entity.ExamRecord;
import com.dimple.entity.ExamStudent;
import com.dimple.dao.ExamStudentDao;
import com.dimple.entity.Question;
import com.dimple.entity.Score;
import com.dimple.service.ExamStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试卷和学生的关联表(ExamStudent)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:39:02
 */
@Service
public class ExamStudentServiceImpl implements ExamStudentService {
    @Autowired
    private ExamStudentDao examStudentDao;

    @Autowired
    ExamRecordDao examRecordDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    ExamQuestionDao examQuestionDao;


    /**
     * 通过ID查询单条数据
     *
     * @param esId 主键
     * @return 实例对象
     */
    @Override
    public ExamStudent queryById(Integer esId) {
        return this.examStudentDao.queryById(esId);
    }


    /**
     * 新增数据
     *
     * @param examStudent 实例对象
     * @return 实例对象
     */
    @Override
    public ExamStudent insert(ExamStudent examStudent) {
        this.examStudentDao.insert(examStudent);
        return examStudent;
    }

    /**
     * 修改数据
     *
     * @param examStudent 实例对象
     * @return 实例对象
     */
    @Override
    public ExamStudent update(ExamStudent examStudent) {
        this.examStudentDao.update(examStudent);
        return this.queryById(examStudent.getEsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param esId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer esId) {
        return this.examStudentDao.deleteById(esId) > 0;
    }

    @Override
    @Transactional
    public int finishExam(Integer examId, Integer stuId) {
        //阅卷客观题
        ReadingExamObjective(examId, stuId);
        return examStudentDao.updateStatusByExamIdAndStuId(examId, stuId, "1");
    }

    @Override
    public ExamStudent queryByExamIdAndStuId(Integer examId, Integer stuId) {
        return examStudentDao.selectByExamIdAndStuId(examId, stuId);
    }

    @Override
    public List<Score> findScoreList(Score score) {
        return examStudentDao.selectScoreList(score);
    }

    /**
     * 批阅所有的客观题
     *
     * @param examId
     * @param stuId
     */
    private void ReadingExamObjective(Integer examId, Integer stuId) {
        //查询出所有的question id
        List<ExamQuestion> examQuestions = examQuestionDao.selectExamQuestionListByExamId(examId);
        //获取所有的题的数量
        int count = examQuestions.size();
        double totalScore = 0;//试卷考试总分
        int index = 0;//试题数量
        for (ExamQuestion examQuestion : examQuestions) {
            //获取对应的question的信息
            Question question = questionDao.queryById(examQuestion.getQuestionId());

            ExamRecord examRecord = examRecordDao.selectRecordByExamIdAndQuestionIdAndStuId(examId, question.getId(), stuId);
            switch (question.getType()) {
                //单选和多选,判断
                case "1":
                case "2":
                case "4":
                    if (question.getAnswer().equals(examRecord.getAnswer())) {
                        examRecord.setFinalScore(question.getScore());
                        totalScore += question.getScore();
                    } else {
                        examRecord.setFinalScore(0d);
                    }
                    index++;
                    break;
            }
            examRecordDao.updateRecordFinalScore(examRecord);
        }
        //如果这两者相等，说明只有客观题，不需要老师来review
        if (index == count) {
            //更新试卷的状态为已阅
            examStudentDao.updateReadingAndTotalScoreByStuIdAndExamId(stuId, examId, totalScore, "0");
        }
    }
}