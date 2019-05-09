package com.dimple.service.impl;

import com.dimple.entity.ExamQuestion;
import com.dimple.dao.ExamQuestionDao;
import com.dimple.service.ExamQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试卷和问题的关联表(ExamQuestion)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:39:01
 */
@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Resource
    private ExamQuestionDao examQuestionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param eqId 主键
     * @return 实例对象
     */
    @Override
    public ExamQuestion queryById(Integer eqId) {
        return this.examQuestionDao.queryById(eqId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ExamQuestion> queryAllByLimit(int offset, int limit) {
        return this.examQuestionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param examQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public ExamQuestion insert(ExamQuestion examQuestion) {
        this.examQuestionDao.insert(examQuestion);
        return examQuestion;
    }

    /**
     * 修改数据
     *
     * @param examQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public ExamQuestion update(ExamQuestion examQuestion) {
        this.examQuestionDao.update(examQuestion);
        return this.queryById(examQuestion.getEqId());
    }

    /**
     * 通过主键删除数据
     *
     * @param eqId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer eqId) {
        return this.examQuestionDao.deleteById(eqId) > 0;
    }
}