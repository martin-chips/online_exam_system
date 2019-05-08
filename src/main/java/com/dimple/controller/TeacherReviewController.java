package com.dimple.controller;

import com.dimple.entity.Exam;
import com.dimple.entity.ExamRecord;
import com.dimple.service.ExamRecordService;
import com.dimple.service.ExamService;
import com.dimple.service.ExamStudentService;
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
@RequestMapping("/onlineExam/review")
public class TeacherReviewController extends BaseController {

    @Autowired
    ExamService examService;
    @Autowired
    ExamRecordService examRecordService;
    @Autowired
    ExamStudentService examStudentService;

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

    @GetMapping("/{examId}/{studentId}")
    public String reviewStuExam(@PathVariable Integer examId, @PathVariable Integer studentId, Model model) {
        model.addAttribute("exam", examService.findExamDetailToReview(examId, studentId));
        model.addAttribute("stuId", studentId);
        return "onlineExam/review/reviewExam";
    }

    @PostMapping("/reviewExam")
    @ResponseBody
    public AjaxResult reviewExam(ExamRecord examRecord) {
        return toAjax(examRecordService.teacherReviewRecord(examRecord));
    }

    @PostMapping("/finisReview")
    public String finishReview(Integer examId, Integer stuId) {
        examRecordService.finishReview(examId, stuId);
        return "/index";
    }

    @GetMapping("/detail/{examId}/{stuId}")
    public String examDetail(@PathVariable Integer examId, @PathVariable Integer stuId, Model model) {
        model.addAttribute("exam", examService.findExamDetailToReview(examId, stuId));
        model.addAttribute("stuId", stuId);
        model.addAttribute("examStudent", examStudentService.queryByExamIdAndStuId(examId, stuId));
        return "onlineExam/review/detail";
    }
}
