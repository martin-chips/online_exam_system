package com.dimple.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 试卷表(Exam)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:38:58
 */
public class Exam implements Serializable {
    private static final long serialVersionUID = 922283745093904434L;
    
    private Integer examId;
    //试卷名
    private String examName;
    //考试开始时间
    private Date examStartDate;
    //考试持续的时间，单位为分钟
    private Long examLastTime;
    //创建者
    private Long createBy;
    
    private Date createDate;
    
    private Long updateBy;
    
    private Date updateDate;


    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getExamStartDate() {
        return examStartDate;
    }

    public void setExamStartDate(Date examStartDate) {
        this.examStartDate = examStartDate;
    }

    public Long getExamLastTime() {
        return examLastTime;
    }

    public void setExamLastTime(Long examLastTime) {
        this.examLastTime = examLastTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}