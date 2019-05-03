package com.dimple.entity;

import com.dimple.utils.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 公告管理(Notice)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:41:45
 */
@Data
public class Notice extends BaseEntity {
    private static final long serialVersionUID = -21992622161257575L;
    //公告的id
    private Integer noticeId;
    //公告的标题
    private String title;
    //公告的内容
    private String content;
    //公告的类型：1表示需要弹框提示2表示页面提示
    private String type;
}