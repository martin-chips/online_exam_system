package com.dimple.service.impl;

import com.dimple.entity.Exam;
import com.dimple.dao.ExamDao;
import com.dimple.service.ExamService;
import com.dimple.utils.Convert;
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
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Exam exam) {
        return this.examDao.insert(exam);
    }

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Exam exam) {
        return this.examDao.update(exam);
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

    @Override
    public int deleteByIds(String ids) {
        return examDao.deleteByIds(Convert.toIntArray(ids));
    }

    @Override
    public List<Exam> findExamList(Exam exam) {
        return examDao.queryAll(exam);
    }
}