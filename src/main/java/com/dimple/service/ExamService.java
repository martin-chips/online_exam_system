package com.dimple.service;

import com.dimple.entity.Exam;
import com.dimple.entity.StudentExamDetail;

import java.util.List;

/**
 * 试卷表(Exam)表服务接口
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
public interface ExamService {

    /**
     * 通过ID查询单条数据
     *
     * @param examId 主键
     * @return 实例对象
     */
    Exam queryById(Integer examId);

    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    int insert(Exam exam);

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    int update(Exam exam);

    /**
     * 通过主键删除数据
     *
     * @param examId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer examId);

    int deleteByIds(String ids);

    /**
     * 批量获取exam
     *
     * @param exam exam条件
     * @return
     */
    List<Exam> findExamList(Exam exam);

    /**
     * 根据examId获取学生的考试的试卷信息
     *
     * @param id
     * @param examId
     * @return
     */
    StudentExamDetail getExamForStudentByExamId(Integer id, Integer examId);

    List<Exam> findExamListForStu(Exam exam, Integer id);

    /**
     * 查找需要review的试卷
     *
     * @param exam
     * @return
     */
    List<Exam> findExamListToReview(Exam exam);

    /**
     * 获取exam的详细信息，方便老师review
     *
     * @param examId
     * @param stuId
     * @return
     */
    StudentExamDetail findExamDetailToReview(Integer examId, Integer stuId);
}