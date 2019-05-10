
package com.dimple.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 给前端传递考试数据的载体
 */
@Data
public class StudentExamDetail {
    private Integer examId;
    //试卷名称
    private String examName;
    //考试持续时间
    private Long lastTime;
    //考试开始时间
    private Date startDate;
    //试卷的总分
    private Double score;
    //学生考试的总分
    private Double totalScore;

    private List<Question> radioQuestion;
    private List<Question> checkboxQuestion;
    private List<Question> judgeQuestion;
    private List<Question> shortQuestion;
    private List<Question> balckQuestion;
}
