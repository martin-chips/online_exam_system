package com.dimple.service;

import com.dimple.entity.ExamStudent;
import java.util.List;

/**
 * 试卷和学生的关联表(ExamStudent)表服务接口
 *
 * @author makejava
 * @since 2019-05-01 11:39:02
 */
public interface ExamStudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param esId 主键
     * @return 实例对象
     */
    ExamStudent queryById(Integer esId);

    /**
     * 新增数据
     *
     * @param examStudent 实例对象
     * @return 实例对象
     */
    ExamStudent insert(ExamStudent examStudent);

    /**
     * 修改数据
     *
     * @param examStudent 实例对象
     * @return 实例对象
     */
    ExamStudent update(ExamStudent examStudent);

    /**
     * 通过主键删除数据
     *
     * @param esId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer esId);

}