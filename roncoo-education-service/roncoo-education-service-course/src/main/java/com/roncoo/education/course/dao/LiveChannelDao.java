package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveChannel;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveChannelExample;

import java.util.List;

/**
 * 直播频道信息 服务类
 *
 * @author fengyw
 * @date 2024-06-08
 */
public interface LiveChannelDao {

    /**
     * 保存直播频道信息
     *
     * @param record 直播频道信息
     * @return 影响记录数
     */
    int save(LiveChannel record);

    /**
     * 根据ID删除直播频道信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改直播频道信息
     *
     * @param record 直播频道信息
     * @return 影响记录数
     */
    int updateById(LiveChannel record);

    /**
     * 根据ID获取直播频道信息
     *
     * @param id 主键ID
     * @return 直播频道信息
     */
    LiveChannel getById(Long id);

    /**
     * 直播频道信息--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<LiveChannel> page(int pageCurrent, int pageSize, LiveChannelExample example);

    /**
     * 直播频道信息--条件列出
     *
     * @param example 查询条件
     * @return 直播频道信息列表
     */
    List<LiveChannel> listByExample(LiveChannelExample example);

    /**
     * 直播频道信息--条件统计
     *
     * @param example 统计条件
     * @return 直播频道信息数量
     */
    int countByExample(LiveChannelExample example);

    LiveChannel getByCourseId(Long courseId);
}
