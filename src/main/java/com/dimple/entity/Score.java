package com.dimple.entity;

import lombok.Data;

/**
 * 学生成绩管理封装
 */
@Data
public class Score {
    /**
     * 学生的id
     */
    private Integer stuId;
    /**
     * 学生学号
     */
    private String stuNum;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 试卷的id
     */
    private int examId;
    /**
     * 试卷名称
     */
    private String examName;
    /**
     * 总成绩
     */
    private Double totalScore;
    /**
     * 试卷是否已经批阅
     */
    private String reading;
    /**
     * 试卷状态
     */
    private String status;

}
