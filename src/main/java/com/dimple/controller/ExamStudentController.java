package com.dimple.controller;

import com.dimple.entity.ExamStudent;
import com.dimple.service.ExamStudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 试卷和学生的关联表(ExamStudent)表控制层
 *
 * @author makejava
 * @since 2019-05-01 11:39:02
 */
@RestController
@RequestMapping("examStudent")
public class ExamStudentController {
    /**
     * 服务对象
     */
    @Resource
    private ExamStudentService examStudentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ExamStudent selectOne(Integer id) {
        return this.examStudentService.queryById(id);
    }

}