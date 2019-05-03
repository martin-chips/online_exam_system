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
    SysLog queryById(Integer id);


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

    /**
     * 批量获取log
     * @param sysLog
     * @return
     */
    List<SysLog> selectLogList(SysLog sysLog);

    int deleteSysLogByIds(String ids);

}