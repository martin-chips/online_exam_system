package com.dimple.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 试卷和问题的关联表(ExamQuestion)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
@Data
public class ExamQuestion implements Serializable {
    private static final long serialVersionUID = -58772574298959795L;

    private Integer eqId;
    //试卷的id
    private Integer examId;
    //问题的id
    private Integer questionId;

}