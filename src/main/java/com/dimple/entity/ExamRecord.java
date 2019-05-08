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


    private Integer examId;

    private Integer stuId;

    private Integer questionId;

    private String answer;

    private Double finalScore = 0D;


}