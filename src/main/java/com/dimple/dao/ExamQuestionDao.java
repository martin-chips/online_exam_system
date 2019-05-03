package com.dimple.dao;

import com.dimple.entity.ExamQuestion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 试卷和问题的关联表(ExamQuestion)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
public interface ExamQuestionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param eqId 主键
     * @return 实例对象
     */
    ExamQuestion queryById(Integer eqId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExamQuestion> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param examQuestion 实例对象
     * @return 对象列表
     */
    List<ExamQuestion> queryAll(ExamQuestion examQuestion);

    /**
     * 新增数据
     *
     * @param examQuestion 实例对象
     * @return 影响行数
     */
    int insert(ExamQuestion examQuestion);

    /**
     * 修改数据
     *
     * @param examQuestion 实例对象
     * @return 影响行数
     */
    int update(ExamQuestion examQuestion);

    /**
     * 通过主键删除数据
     *
     * @param eqId 主键
     * @return 影响行数
     */
    int deleteById(Integer eqId);

    /**
     * 根据试卷的id删除与question的关联
     * @param examId
     * @return
     */
    int deleteByExamId(Integer examId);

    /**
     * 根据Exam的id查询出所有的examQuestion实体
     * @param examId
     * @return
     */
    List<ExamQuestion> selectExamQuestionListByExamId(Integer examId);
}