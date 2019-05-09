package com.dimple.service.impl;

import com.dimple.entity.Notice;
import com.dimple.dao.NoticeDao;
import com.dimple.service.NoticeService;
import com.dimple.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告管理(Notice)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:41:47
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param noticeId 主键
     * @return 实例对象
     */
    @Override
    public Notice queryById(Integer noticeId) {
        return this.noticeDao.queryById(noticeId);
    }


    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Notice notice) {
        return this.noticeDao.insert(notice);

    }

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Notice notice) {
        return this.noticeDao.update(notice);
    }

    /**
     * 通过主键删除数据
     *
     * @param noticeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer noticeId) {
        return this.noticeDao.deleteById(noticeId) > 0;
    }

    @Override
    public int deleteByIds(String ids) {
        return noticeDao.deleteByIds(Convert.toIntArray(ids));
    }

    @Override
    public List<Notice> findNoticeList(Notice notice) {
        return noticeDao.queryAll(notice);
    }

}