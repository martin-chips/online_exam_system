package com.dimple.entity;

import java.io.Serializable;

/**
 * 试卷和学生的关联表(ExamStudent)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:39:01
 */
public class ExamStudent implements Serializable {
    private static final long serialVersionUID = -57626389942460266L;
    
    private Integer esId;
    //学生的id
    private Integer studentId;
    //试卷的id
    private Integer examId;


    public Integer getEsId() {
        return esId;
    }

    public void setEsId(Integer esId) {
        this.esId = esId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

}