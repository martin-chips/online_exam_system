package com.dimple.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.dimple.dao.ExamStudentDao;
import com.dimple.entity.ExamRecord;
import com.dimple.dao.ExamRecordDao;
import com.dimple.service.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考试记录(ExamRecord)表服务实现类
 *
 * @author makejava
 * @since 2019-05-04 01:11:58
 */
@Service
public class ExamRecordServiceImpl implements ExamRecordService {
    @Resource
    private ExamRecordDao examRecordDao;
    @Autowired
    ExamStudentDao examStudentDao;

    @Override
    public int insertOrUpdateRecord(ExamRecord record) {
        if (record == null || record.getExamId() == null || record.getAnswer() == null || record.getQuestionId() == null) {
            return -1;
        }
        return examRecordDao.insertOrUpdateRecord(record);
    }

    @Override
    public int teacherReviewRecord(ExamRecord examRecord) {
        return examRecordDao.updateRecordFinalScore(examRecord);
    }

    @Override
    public void finishReview(Integer examId, Integer stuId) {
        //根据Exam的id和stud的id查询出所有试题的记录，计算总得分
        List<ExamRecord> records = examRecordDao.selectRecordByExamIdAndStuId(examId, stuId);
        //遍历record
        double score = 0;
        for (ExamRecord record : records) {
            score += record.getFinalScore();
        }
        examStudentDao.updateReadingAndTotalScoreByStuIdAndExamId(stuId, examId, score, "0");
    }
}