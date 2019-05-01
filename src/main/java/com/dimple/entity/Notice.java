package com.dimple.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 公告管理(Notice)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:41:45
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = -21992622161257575L;
    //公告的id
    private Integer noticeId;
    //公告的标题
    private String title;
    //公告的内容
    private String content;
    //公告的类型：1表示需要弹框提示2表示页面提示
    private String type;
    
    private Long createBy;
    
    private Date createDate;
    
    private Long updateBy;
    
    private Date updateDate;


    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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