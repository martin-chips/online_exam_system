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
    SysUser selectSysyUserById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> selectUserList(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过登录账号查询用户
     * @param username
     * @return
     */
    SysUser findSysUserByLoginName(String username);

    List<SysUser> selectUserList(SysUser sysUser);
}