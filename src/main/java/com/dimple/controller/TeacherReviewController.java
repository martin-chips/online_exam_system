package com.dimple.controller;

import com.dimple.annotation.Log;
import com.dimple.entity.Exam;
import com.dimple.entity.ExamRecord;
import com.dimple.entity.SysUser;
import com.dimple.service.ExamRecordService;
import com.dimple.service.ExamService;
import com.dimple.service.ExamStudentService;
import com.dimple.service.SysUserService;
import com.dimple.utils.web.AjaxResult;
import com.dimple.utils.web.BaseController;
import com.dimple.utils.web.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 教师批卷
 */
@Controller
//localhhost:8080//onlineExam/review
@RequestMapping("/onlineExam/review")//地址栏中的url前缀
public class TeacherReviewController extends BaseController {
    //注入对象
    @Autowired
    ExamService examService;
    @Autowired
    ExamRecordService examRecordService;
    @Autowired
    ExamStudentService examStudentService;
    @Autowired
    SysUserService sysUserService;

    //当url
    @GetMapping()
    public String review() {
        return "onlineExam/review/review";
    }

    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Exam exam) {
        startPage();
        //exam.setReviewerId(getSysUser().getId());
        List<Exam> exams = examService.findExamListToReview(exam);
        return getDataTable(exams);
    }

    /**
     * 批卷
     */
    @GetMapping("/{examId}/{studentId}")
    public String reviewStuExam(@PathVariable Integer examId, @PathVariable Integer studentId, Model model) {
        model.addAttribute("exam", examService.findExamDetailToReview(examId, studentId));
        model.addAttribute("stuId", studentId);
        return "onlineExam/review/reviewExam";
    }

    //异步提交并更新单个试题的得分
    @PostMapping("/reviewExam")
    @ResponseBody
    public AjaxResult reviewExam(ExamRecord examRecord) {
        return toAjax(examRecordService.teacherReviewRecord(examRecord));
    }

    //完成阅卷，提交阅卷的结果，返回index界面，并更新e—s中，total-Score的值
    @PostMapping("/finisReview")
    @Log("完成阅卷")
    public String finishReview(Integer examId, Integer stuId) {
        examRecordService.finishReview(examId, stuId);
        return "/index";
    }

    //展示试卷的详情，阅卷完成详情
    @GetMapping("/detail/{examId}/{stuId}")
    public String examDetail(@PathVariable Integer examId, @PathVariable Integer stuId, Model model) {
        model.addAttribute("exam", examService.findExamDetailToReview(examId, stuId));
        model.addAttribute("student", sysUserService.selectSysyUserById(stuId));
        model.addAttribute("examStudent", examStudentService.queryByExamIdAndStuId(examId, stuId));
        return "onlineExam/review/detail";
    }
}
