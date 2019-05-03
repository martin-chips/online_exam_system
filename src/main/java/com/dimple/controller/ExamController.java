package com.dimple.controller;

import com.dimple.entity.Exam;
import com.dimple.service.ExamService;
import com.dimple.utils.web.AjaxResult;
import com.dimple.utils.web.BaseController;
import com.dimple.utils.web.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试卷表(Exam)表控制层
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
@Controller
@RequestMapping("onlineExam/exam")
public class ExamController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private ExamService examService;

    @GetMapping()
    public String exam() {
        return "onlineExam/exam/exam";
    }

    @GetMapping("/list")
    public TableDataInfo list(Exam exam) {
        startPage();
        List<Exam> exams = examService.findExamList(exam);
        return getDataTable(exams);
    }

    @GetMapping("/add")
    public String add() {
        return "onlineExam/exam/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Exam exam) {
        return toAjax(examService.insert(exam));
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("exam", examService.queryById(id));
        return "onlineExam/exam/update";
    }

    @PutMapping("/update")
    @ResponseBody
    public AjaxResult updateSave(Exam exam) {
        return toAjax(examService.update(exam));
    }

    @DeleteMapping()
    @ResponseBody
    public AjaxResult delete(String ids) {
        return toAjax(examService.deleteByIds(ids));
    }
}