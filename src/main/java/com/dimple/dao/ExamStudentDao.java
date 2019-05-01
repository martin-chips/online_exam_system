package com.dimple.dao;

import com.dimple.entity.ExamStudent;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 试卷和学生的关联表(ExamStudent)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-01 11:39:01
 */
public interface ExamStudentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param esId 主键
     * @return 实例对象
     */
    ExamStudent queryById(Integer esId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExamStudent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param examStudent 实例对象
     * @return 对象列表
     */
    List<ExamStudent> queryAll(ExamStudent examStudent);

    /**
     * 新增数据
     *
     * @param examStudent 实例对象
     * @return 影响行数
     */
    int insert(ExamStudent examStudent);

    /**
     * 修改数据
     *
     * @param examStudent 实例对象
     * @return 影响行数
     */
    int update(ExamStudent examStudent);

    /**
     * 通过主键删除数据
     *
     * @param esId 主键
     * @return 影响行数
     */
    int deleteById(Integer esId);

}