package com.dimple.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 试卷和学生的关联表(ExamStudent)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:39:01
 */
@Data
public class ExamStudent implements Serializable {
    private static final long serialVersionUID = -57626389942460266L;

    private Integer esId;
    //学生的id
    private Integer studentId;
    //试卷的id
    private Integer examId;
    //0表示未参加考试，1表示已经参加考试
    private String status;

    //是否已经阅卷
    private String reading;

    //最终成绩
    private Double totalScore;
}