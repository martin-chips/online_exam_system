package com.dimple.service;

import com.dimple.entity.Question;

import java.util.List;

/**
 * 问题表(Question)表服务接口
 *
 * @author makejava
 * @since 2019-05-01 11:39:04
 */
public interface QuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Question queryById(Integer id);

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    int insert(Question question);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    int update(Question question);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据Id删除question
     *
     * @param ids 需要删除的id的集合
     * @return 受影响的行数
     */
    int deleteByIds(String ids);

    /**
     * 查询集合
     *
     * @param question 问题
     * @return 集合
     */
    List<Question> findQuestionList(Question question);
}