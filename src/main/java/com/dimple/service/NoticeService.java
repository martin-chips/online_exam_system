package com.dimple.service;

import com.dimple.entity.Notice;

import java.util.List;

/**
 * 公告管理(Notice)表服务接口
 *
 * @author makejava
 * @since 2019-05-01 11:41:46
 */
public interface NoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param noticeId 主键
     * @return 实例对象
     */
    Notice queryById(Integer noticeId);



    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    int insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    int update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param noticeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer noticeId);

    /**
     * 批量删除id
     * @param ids 需要删除的id
     * @return 受影响的行数
     */
    int deleteByIds(String ids);

    /**
     * 批量获取notice集合
     * @param notice
     * @return
     */
    List<Notice> findNoticeList(Notice notice);
}