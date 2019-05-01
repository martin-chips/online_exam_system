package com.dimple.service.impl;

import com.dimple.entity.Exam;
import com.dimple.dao.ExamDao;
import com.dimple.service.ExamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试卷表(Exam)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {
    @Resource
    private ExamDao examDao;

    /**
     * 通过ID查询单条数据
     *
     * @param examId 主键
     * @return 实例对象
     */
    @Override
    public Exam queryById(Integer examId) {
        return this.examDao.queryById(examId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Exam> queryAllByLimit(int offset, int limit) {
        return this.examDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public Exam insert(Exam exam) {
        this.examDao.insert(exam);
        return exam;
    }

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public Exam update(Exam exam) {
        this.examDao.update(exam);
        return this.queryById(exam.getExamId());
    }

    /**
     * 通过主键删除数据
     *
     * @param examId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer examId) {
        return this.examDao.deleteById(examId) > 0;
    }
}