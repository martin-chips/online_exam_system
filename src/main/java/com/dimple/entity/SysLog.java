package com.dimple.entity;

import com.dimple.utils.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统日志(SysLog)实体类
 *
 * @author makejava
 * @since 2019-05-01 00:15:14
 */
@Data
public class SysLog extends BaseEntity {
    private static final long serialVersionUID = -58935303640827809L;
    //编号
    private int id;
    //请求类型
    private String type;
    //日志标题
    private String title;
    //操作IP地址
    private String remoteAddr;
    //操作用户昵称
    private String username;
    //请求URI
    private String requestUri;
    //操作方式
    private String httpMethod;
    //请求类型.方法
    private String classMethod;
    //操作提交的数据
    private String data;
    //sessionId
    private String sessionId;
    //返回内容
    private String response;
    //方法执行时间
    private Long useTime;
    //浏览器信息
    private String browser;
    //地区
    private String area;
    //省
    private String province;
    //市
    private String city;
    //网络服务提供商
    private String isp;
    //异常信息
    private String exception;

}