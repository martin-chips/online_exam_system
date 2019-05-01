package com.dimple.service;

import com.dimple.entity.SysLog;
import java.util.List;

/**
 * 系统日志(SysLog)表服务接口
 *
 * @author makejava
 * @since 2019-05-01 00:15:14
 */
public interface SysLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    SysLog insert(SysLog sysLog);

    /**
     * 修改数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    SysLog update(SysLog sysLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}