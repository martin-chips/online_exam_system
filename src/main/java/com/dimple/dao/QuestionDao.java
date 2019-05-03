package com.dimple.dao;

import com.dimple.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题表(Question)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-01 11:39:04
 */
public interface QuestionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Question queryById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param question 实例对象
     * @return 对象列表
     */
    List<Question> queryAll(Question question);

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int insert(Question question);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 影响行数
     */
    int update(Question question);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的id的集合
     * @return 受影响的行数
     */
    int deleteByIds(Integer[] ids);
}