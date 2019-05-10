package com.dimple.controller;

import com.dimple.annotation.Log;
import com.dimple.entity.Question;
import com.dimple.service.QuestionService;
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
 * 问题表(Question)表控制层
 *
 * @author makejava
 * @since 2019-05-01 11:39:04
 */
@Controller
@RequestMapping("onlineExam/question")
public class QuestionController extends BaseController {

    @Autowired
    QuestionService questionService;

    @GetMapping()
    public String question() {
        return "onlineExam/question/question";
    }

    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Question question) {
        startPage();
        List<Question> questions = questionService.findQuestionList(question);
        return getDataTable(questions);
    }

    @GetMapping("/add")
    public String add() {
        return "onlineExam/question/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @Log("添加试题")
    public AjaxResult addSave(Question question) {
        return toAjax(questionService.insert(question));
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("question", questionService.queryById(id));
        return "/onlineExam/question/update";
    }

    @PutMapping("/update")
    @Log("修改试题")
    @ResponseBody
    public AjaxResult updateSave(Question question) {
        return toAjax(questionService.update(question));
    }

    @DeleteMapping()
    @ResponseBody
    @Log("删除试题")
    public AjaxResult delete(String ids) {
        return toAjax(questionService.deleteByIds(ids));
    }
}