package com.dimple.controller;

import com.dimple.entity.ExamQuestion;
import com.dimple.service.ExamQuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 试卷和问题的关联表(ExamQuestion)表控制层
 *
 * @author makejava
 * @since 2019-05-01 11:39:01
 */
@RestController
@RequestMapping("examQuestion")
public class ExamQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private ExamQuestionService examQuestionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ExamQuestion selectOne(Integer id) {
        return this.examQuestionService.queryById(id);
    }

}