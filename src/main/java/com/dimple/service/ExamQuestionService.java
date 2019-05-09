package com.dimple.service;

import com.dimple.entity.ExamQuestion;

import java.util.List;

/**
 * 试卷和问题的关联表(ExamQuestion)表服务接口
 *
 * @author makejava
 * @since 2019-05-01 11:39:01
 */
public interface ExamQuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param eqId 主键
     * @return 实例对象
     */
    ExamQuestion queryById(Integer eqId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ExamQuestion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param examQuestion 实例对象
     * @return 实例对象
     */
    ExamQuestion insert(ExamQuestion examQuestion);

    /**
     * 修改数据
     *
     * @param examQuestion 实例对象
     * @return 实例对象
     */
    ExamQuestion update(ExamQuestion examQuestion);

    /**
     * 通过主键删除数据
     *
     * @param eqId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer eqId);

}