package com.dimple.service.impl;

import com.dimple.entity.SysLog;
import com.dimple.dao.SysLogDao;
import com.dimple.service.SysLogService;
import com.dimple.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统日志(SysLog)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 00:15:14
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogDao sysLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysLog queryById(Integer id) {
        return this.sysLogDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysLog insert(SysLog sysLog) {
        this.sysLogDao.insert(sysLog);
        return sysLog;
    }

    /**
     * 修改数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysLog update(SysLog sysLog) {
        this.sysLogDao.update(sysLog);
        return this.queryById(sysLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysLogDao.deleteById(id) > 0;
    }

    @Override
    public List<SysLog> selectLogList(SysLog sysLog) {
        return sysLogDao.queryAll(sysLog);
    }

    @Override
    public int deleteSysLogByIds(String ids) {
        return sysLogDao.deleteByIds(Convert.toIntArray(ids));
    }
}