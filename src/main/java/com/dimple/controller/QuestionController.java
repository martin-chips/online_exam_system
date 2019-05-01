package com.dimple.controller;

import com.dimple.entity.Question;
import com.dimple.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 问题表(Question)表控制层
 *
 * @author makejava
 * @since 2019-05-01 11:39:04
 */
@RestController
@RequestMapping("question")
public class QuestionController {
    /**
     * 服务对象
     */
    @Resource
    private QuestionService questionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Question selectOne(Integer id) {
        return this.questionService.queryById(id);
    }

}