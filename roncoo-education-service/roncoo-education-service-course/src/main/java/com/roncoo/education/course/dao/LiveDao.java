package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Live;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveExample;

import java.util.List;

/**
 * 直播信息 服务类
 *
 * @author fengyw
 * @date 2024-06-07
 */
public interface LiveDao {

    /**
     * 保存直播信息
     *
     * @param record 直播信息
     * @return 影响记录数
     */
    int save(Live record);

    /**
     * 根据ID删除直播信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改直播信息
     *
     * @param record 直播信息
     * @return 影响记录数
     */
    int updateById(Live record);

    /**
     * 根据ID获取直播信息
     *
     * @param id 主键ID
     * @return 直播信息
     */
    Live getById(Long id);

    /**
     * 直播信息--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<Live> page(int pageCurrent, int pageSize, LiveExample example);

    /**
     * 直播信息--条件列出
     *
     * @param example 查询条件
     * @return 直播信息列表
     */
    List<Live> listByExample(LiveExample example);

    /**
     * 直播信息--条件统计
     *
     * @param example 统计条件
     * @return 直播信息数量
     */
    int countByExample(LiveExample example);

    List<Live> listByIds(List<Long> liveIdList);
}
