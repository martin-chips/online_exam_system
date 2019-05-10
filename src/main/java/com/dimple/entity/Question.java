package com.dimple.entity;

import com.dimple.utils.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 问题表(Question)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:39:03
 */
@Data
public class Question extends BaseEntity {
    private static final long serialVersionUID = 850681683013092951L;

    private Integer id;
    //问题类型：1表示单选，2表示多选，3表示天空，4表示判断，5表示问答
    private String type;
    //题干
    private String title;
    //A选项答案
    private String optionA;
    //B选项答案
    private String optionB;
    //C选项答案
    private String optionC;
    //D选项答案
    private String optionD;
    //答案
    private String answer;
    //解析
    private String analyse;
    //分值
    private Double score;
    //该题的最终得分
    private Double finalScore;

    //单选或者多选的选中（修改试题的正确答案回显）
    private String optionAChecked;
    private String optionBChecked;
    private String optionCChecked;
    private String optionDChecked;
    //判断题的选中
    private String judgeAnswer1;//正确
    private String judgeAnswer0;//错误

    //学生的答案（查看 详情和考试过程中的）
    private String textAnswerStu;
    private String optionACheckedStu;
    private String optionBCheckedStu;
    private String optionCCheckedStu;
    private String optionDCheckedStu;
    private String judgeAnswer1Stu;//正确
    private String judgeAnswer0Stu;//错误


}