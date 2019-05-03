package com.dimple.service.impl;

import com.dimple.dao.ExamQuestionDao;
import com.dimple.dao.ExamStudentDao;
import com.dimple.entity.Exam;
import com.dimple.dao.ExamDao;
import com.dimple.entity.ExamQuestion;
import com.dimple.entity.ExamStudent;
import com.dimple.service.ExamService;
import com.dimple.utils.Convert;
import com.dimple.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 试卷表(Exam)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {
    @Resource
    private ExamDao examDao;
    @Autowired
    private ExamQuestionDao examQuestionDao;
    @Autowired
    ExamStudentDao examStudentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param examId 主键
     * @return 实例对象
     */
    @Override
    public Exam queryById(Integer examId) {
        Exam exam = this.examDao.queryById(examId);
        if (exam == null) {
            return null;
        }
        //设置其日期的格式
        List<ExamQuestion> examQuestions = examQuestionDao.selectExamQuestionListByExamId(examId);
        Integer ids[] = new Integer[examQuestions.size()];
        String idsStr = "";
        for (int i = 0; i < examQuestions.size(); i++) {
            ids[i] = examQuestions.get(i).getQuestionId();
            idsStr += examQuestions.get(i).getQuestionId() + ",";
        }
        exam.setIds(ids);
        exam.setIdsStr(idsStr);
        //查询出需要参加考试的学生的信息
        ExamStudent examStudent = new ExamStudent();
        examStudent.setExamId(examId);
        List<ExamStudent> examStudents = examStudentDao.queryAll(examStudent);
        Integer[] studentIds = new Integer[examStudents.size()];
        for (int i = 0; i < examStudents.size(); i++) {
            studentIds[i] = examStudents.get(i).getStudentId();
        }
        exam.setStudentIds(studentIds);
        return exam;
    }


    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int insert(Exam exam) {
        Integer[] ids = exam.getIds();
        int insert = this.examDao.insert(exam);
        if (ids != null) {
            //设置exam和question的关联
            for (Integer id : ids) {
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setExamId(exam.getExamId());
                examQuestion.setQuestionId(id);
                examQuestionDao.insert(examQuestion);
            }
        }
        //获取参加考试的学生的信息
        Integer[] studentIds = exam.getStudentIds();
        if (studentIds != null) {
            for (Integer studentId : studentIds) {
                ExamStudent examStudent = new ExamStudent();
                examStudent.setExamId(exam.getExamId());
                examStudent.setStudentId(studentId);
                examStudentDao.insert(examStudent);
            }
        }
        return insert;
    }

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int update(Exam exam) {
        //删除关联
        Integer[] ids = exam.getIds();
        //删除关联，根据examid
        examQuestionDao.deleteByExamId(exam.getExamId());
        //删除和学生的关联
        examStudentDao.deleteByExamId(exam.getExamId());
        //重新设置关联
        if (ids != null) {
            for (Integer id : ids) {
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setExamId(exam.getExamId());
                examQuestion.setQuestionId(id);
                examQuestionDao.insert(examQuestion);
            }
        }
        //重置和学生的关联
        if (exam.getStudentIds() != null) {
            for (Integer studentId : exam.getStudentIds()) {
                ExamStudent examStudent = new ExamStudent();
                examStudent.setExamId(exam.getExamId());
                examStudent.setStudentId(studentId);
                examStudentDao.insert(examStudent);
            }
        }
        return this.examDao.update(exam);
    }

    /**
     * 通过主键删除数据
     *
     * @param examId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer examId) {
        return this.examDao.deleteById(examId) > 0;
    }

    @Override
    @Transactional
    public int deleteByIds(String ids) {
        Integer[] examIds = Convert.toIntArray(ids);
        return examDao.deleteByIds(examIds);
    }

    @Override
    public List<Exam> findExamList(Exam exam) {
        return examDao.queryAll(exam);
    }
}