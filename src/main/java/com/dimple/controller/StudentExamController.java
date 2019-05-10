package com.dimple.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.dimple.annotation.Log;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生考试
 */
@Controller
@RequestMapping("/exam/student")
public class StudentExamController extends BaseController {

    @Autowired
    ExamService examService;
    @Autowired
    ExamRecordService examRecordService;
    @Autowired
    ExamStudentService examStudentService;

    /**
     * 跳转到考试列表界面
     */
    @GetMapping()
    public String exam() {
        return "exam/exam";
    }


    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Exam exam) {
        //设置只有在当前时间之前的考试可见
        Map map = new HashMap();
        map.put("endTime", new SimpleDateFormat("yyyy-MM-dd hh:ss:mm").format(new Date()));
        exam.setParams(map);
        startPage();
        List<Exam> examList = examService.findExamListForStu(exam, getSysUser().getId());
        return getDataTable(examList);
    }

    /**
     * 开始考试
     */
    @Log("开始考试")
    @GetMapping("/{examId}")
    public String startExam(@PathVariable Integer examId, Model model) {
        model.addAttribute("exam", examService.getExamForStudentByExamId(examId, getSysUser().getId()));
        return "exam/makeExam";
    }


    /**
     * 考试记录
     */
    @PostMapping("/record")
    @ResponseBody
    public AjaxResult record(ExamRecord record) {
        record.setStuId(getSysUser().getId());
        int i = examRecordService.insertOrUpdateRecord(record);
        if (i == -1) {
            return AjaxResult.error(500, "数据异常，请刷新页面重试或者联系管理员");
        } else if (i == 1) {
            return AjaxResult.success("当前答案已保存");
        } else if (i == 2) {
            return AjaxResult.success("当前答案已更新");
        } else {
            return toAjax(i);
        }
    }

    /**
     * 结束考试
     *
     * @param examId
     * @return
     */
    @Log("结束考试")
    @PostMapping("/finish")
    @ResponseBody
    public AjaxResult finish(Integer examId) {
        return toAjax(examStudentService.finishExam(examId, getSysUser().getId()));
    }

    @GetMapping("detail/{examId}")
    public String detail(@PathVariable Integer examId, Model model) {
        model.addAttribute("exam", examService.getExamForStudentByExamId(examId, getSysUser().getId()));
        return "exam/detail";
    }
}
