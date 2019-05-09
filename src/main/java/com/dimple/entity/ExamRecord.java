package com.dimple.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 考试记录(ExamRecord)实体类
 *
 * @author makejava
 * @since 2019-05-04 01:11:58
 */
@Data
public class ExamRecord implements Serializable {
    private static final long serialVersionUID = -44611580440370702L;

    /**
     * 试卷的id
     */
    private Integer examId;
    /**
     * 学生的iid
     */
    private Integer stuId;
    /**
     * 试题的id
     */
    private Integer questionId;
    /**
     * 学生端考试传来的答案
     */
    private String answer;
    /**
     * 该道题的最终成绩
     */
    private Double finalScore = 0D;


}