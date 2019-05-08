package com.dimple.service;

import com.dimple.entity.ExamRecord;

import java.util.List;

/**
 * 考试记录(ExamRecord)表服务接口
 *
 * @author makejava
 * @since 2019-05-04 01:11:58
 */
public interface ExamRecordService {


    /**
     * 新增或者插入一条记录
     *
     * @param record
     * @return
     */
    int insertOrUpdateRecord(ExamRecord record);

    /**
     * 教师更新最终成绩
     *
     * @param examRecord
     * @return
     */
    int teacherReviewRecord(ExamRecord examRecord);

    /**
     * 设置完成试卷的review
     * @param examId
     * @param stuId
     */
    void finishReview(Integer examId, Integer stuId);
}