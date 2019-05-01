package com.dimple.entity;

import java.io.Serializable;

/**
 * 问题表(Question)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:39:03
 */
public class Question implements Serializable {
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
    private String analyze;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
    }

}