package com.dimple.entity;

import com.dimple.utils.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 试卷表(Exam)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:38:58
 */
@Data
public class Exam extends BaseEntity {
    private static final long serialVersionUID = 922283745093904434L;

    private Integer examId;
    //试卷名
    private String examName;
    //考试开始时间
    private Date examStartDate;
    //考试持续的时间，单位为分钟
    private Long examLastTime;
}