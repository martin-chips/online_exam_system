package com.dimple.dao;

import com.dimple.entity.ExamRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考试记录(ExamRecord)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-04 01:11:58
 */
public interface ExamRecordDao {

    /**
     * 存在就更新，不存在就新增
     *
     * @param record
     * @return
     */
    int insertOrUpdateRecord(ExamRecord record);

    /**
     * \
     * 根据试卷id和question的id和user的id同步获取答案信息
     *
     * @param examId
     * @param id
     * @param userId
     * @return
     */
    String selectAnswerByExamIdAndQuestionIdAndStuId(@Param("examId") Integer examId, @Param("questionId") Integer id, @Param("stuId") Integer userId);

    /**
     * 根据试卷的id和学生的id查询count记录
     *
     * @param examId
     * @param stuId
     * @return
     */
    int selectRecordCountByExamIdAndStuId(@Param("examId") Integer examId, @Param("stuId") Integer stuId);

    ExamRecord selectRecordByExamIdAndQuestionIdAndStuId(@Param("examId") Integer examId, @Param("questionId") Integer id, @Param("stuId") Integer userId);

    /**
     * 更新其最终成绩
     *
     * @param examRecord
     * @return
     */
    int updateRecordFinalScore(ExamRecord examRecord);


    List<ExamRecord> selectRecordByExamIdAndStuId(@Param("examId") Integer examId, @Param("stuId") Integer stuId);
}