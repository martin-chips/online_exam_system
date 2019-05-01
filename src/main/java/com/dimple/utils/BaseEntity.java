package com.dimple.utils;

import com.dimple.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    protected Long createBy;

    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 更新者
     */
    protected Long updateBy;

    /**
     * 更新日期
     */
    protected Date updateDate;

    /**
     * 删除标记（Y：正常；N：删除；A：审核；）
     */
    protected Boolean delFlag;

    /**
     * 备注
     */
    protected String remarks;

    /**
     * 创建者
     */
    protected SysUser createUser;

    /**
     * 修改者
     */
    protected SysUser updateUser;

}
