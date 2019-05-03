package com.dimple.entity;

import com.dimple.utils.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2019-05-01 00:15:15
 */
@Data
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 421015445214579434L;
    //用户ID
    private Integer id;
    //登录名
    private String loginName;
    //昵称
    private String nickName;

    private String icon;
    //密码
    private String password;
    //shiro加密盐
    private String salt;
    //手机号码
    private String tel;
    //邮箱地址
    private String email;
    //是否锁定
    private Boolean locked;
    //用户类型
    private String userType;

    //在考试管理的时候确定学生是否选中
    private Boolean studentCheckFlag = false;
}