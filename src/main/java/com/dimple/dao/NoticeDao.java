package com.dimple.dao;

import com.dimple.entity.Notice;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 公告管理(Notice)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-01 11:41:46
 */
public interface NoticeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param noticeId 主键
     * @return 实例对象
     */
    Notice queryById(Integer noticeId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param notice 实例对象
     * @return 对象列表
     */
    List<Notice> queryAll(Notice notice);

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 影响行数
     */
    int insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 影响行数
     */
    int update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param noticeId 主键
     * @return 影响行数
     */
    int deleteById(Integer noticeId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByIds(Integer[] ids);
}