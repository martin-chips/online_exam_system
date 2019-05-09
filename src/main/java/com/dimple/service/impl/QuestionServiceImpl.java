package com.dimple.service.impl;

import com.dimple.entity.Question;
import com.dimple.dao.QuestionDao;
import com.dimple.service.QuestionService;
import com.dimple.utils.Convert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题表(Question)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:39:04
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao questionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Question queryById(Integer id) {
        Question question = this.questionDao.queryById(id);
        if (question == null) {
            return null;
        }
        switch (question.getType()) {
            case "1":
            case "2":
                String[] split = question.getAnswer().split(",");
                for (String s : split) {
                    if ("A".equals(s)) {
                        question.setOptionAChecked("A");
                    }
                    if ("B".equals(s)) {
                        question.setOptionBChecked("B");
                    }
                    if ("C".equals(s)) {
                        question.setOptionCChecked("C");
                    }
                    if ("D".equals(s)) {
                        question.setOptionDChecked("D");
                    }
                }
                break;
            case "4":
                if ("1".equals(question.getAnswer())) {
                    question.setJudgeAnswer1("1");
                } else if ("0".equals(question.getAnswer())) {
                    question.setJudgeAnswer0("0");
                }

        }
        return question;
    }


    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Question question) {
        return this.questionDao.insert(question);
    }

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Question question) {
        return this.questionDao.update(question);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.questionDao.deleteById(id) > 0;
    }

    @Override
    public int deleteByIds(String ids) {
        return questionDao.deleteByIds(Convert.toIntArray(ids));
    }

    @Override
    public List<Question> findQuestionList(Question question) {
        return questionDao.queryAll(question);
    }
}