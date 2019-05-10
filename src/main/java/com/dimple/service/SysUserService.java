package com.dimple.service;

import com.dimple.entity.SysUser;

import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2019-05-01 00:15:15
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser selectSysyUserById(Integer id);


    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    int insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 收影响的行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过登录账号查询用户
     *
     * @param username
     * @return
     */
    SysUser findSysUserByLoginName(String username);

    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 根据ids删除用户
     *
     * @param ids id的集合
     * @return 受影响的行数
     */
    int deleteSysUserByIds(String ids);
}