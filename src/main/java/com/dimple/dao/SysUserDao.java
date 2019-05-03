package com.dimple.dao;

import com.dimple.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-01 00:15:15
 */
public interface SysUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser selectUserById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    List<SysUser> queryAll(SysUser sysUser);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据登录名称查询用户
     *
     * @param loginName 登录名称
     * @return 用户
     */
    SysUser findSysUserByLoginName(String loginName);

    /**
     * 根据Id批量删除用户
     *
     * @param ids id的集合
     * @return 受影响的行数
     */
    int deleteByIds(Integer[] ids);
}