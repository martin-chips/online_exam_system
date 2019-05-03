package com.dimple.service.impl;

import com.dimple.entity.ExamStudent;
import com.dimple.dao.ExamStudentDao;
import com.dimple.service.ExamStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试卷和学生的关联表(ExamStudent)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:39:02
 */
@Service("examStudentService")
public class ExamStudentServiceImpl implements ExamStudentService {
    @Resource
    private ExamStudentDao examStudentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param esId 主键
     * @return 实例对象
     */
    @Override
    public ExamStudent queryById(Integer esId) {
        return this.examStudentDao.queryById(esId);
    }


    /**
     * 新增数据
     *
     * @param examStudent 实例对象
     * @return 实例对象
     */
    @Override
    public ExamStudent insert(ExamStudent examStudent) {
        this.examStudentDao.insert(examStudent);
        return examStudent;
    }

    /**
     * 修改数据
     *
     * @param examStudent 实例对象
     * @return 实例对象
     */
    @Override
    public ExamStudent update(ExamStudent examStudent) {
        this.examStudentDao.update(examStudent);
        return this.queryById(examStudent.getEsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param esId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer esId) {
        return this.examStudentDao.deleteById(esId) > 0;
    }
}