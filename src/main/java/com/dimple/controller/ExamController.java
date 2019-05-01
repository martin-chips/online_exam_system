package com.dimple.controller;

import com.dimple.entity.Exam;
import com.dimple.service.ExamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 试卷表(Exam)表控制层
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
@RestController
@RequestMapping("exam")
public class ExamController {
    /**
     * 服务对象
     */
    @Resource
    private ExamService examService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Exam selectOne(Integer id) {
        return this.examService.queryById(id);
    }

}