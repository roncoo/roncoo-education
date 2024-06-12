package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveLog;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveLogExample;

import java.util.List;

/**
 * 直播开播日志 服务类
 *
 * @author fengyw
 * @date 2024-06-08
 */
public interface LiveLogDao {

    /**
     * 保存直播开播日志
     *
     * @param record 直播开播日志
     * @return 影响记录数
     */
    int save(LiveLog record);

    /**
     * 根据ID删除直播开播日志
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改直播开播日志
     *
     * @param record 直播开播日志
     * @return 影响记录数
     */
    int updateById(LiveLog record);

    /**
     * 根据ID获取直播开播日志
     *
     * @param id 主键ID
     * @return 直播开播日志
     */
    LiveLog getById(Long id);

    /**
     * 直播开播日志--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<LiveLog> page(int pageCurrent, int pageSize, LiveLogExample example);

    /**
     * 直播开播日志--条件列出
     *
     * @param example 查询条件
     * @return 直播开播日志列表
     */
    List<LiveLog> listByExample(LiveLogExample example);

    /**
     * 直播开播日志--条件统计
     *
     * @param example 统计条件
     * @return 直播开播日志数量
     */
    int countByExample(LiveLogExample example);

    LiveLog getBySessionId(String sessionId);
}
