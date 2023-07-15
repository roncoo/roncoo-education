package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarousel;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarouselExample;

import java.util.List;

/**
 * 广告信息 服务类
 *
 * @author wujing
 * @date 2022-09-16
 */
public interface WebsiteCarouselDao {

    /**
     * 保存广告信息
     *
     * @param record 广告信息
     * @return 影响记录数
     */
    int save(WebsiteCarousel record);

    /**
     * 根据ID删除广告信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改广告信息
     *
     * @param record 广告信息
     * @return 影响记录数
     */
    int updateById(WebsiteCarousel record);

    /**
     * 根据ID获取广告信息
     *
     * @param id 主键ID
     * @return 广告信息
     */
    WebsiteCarousel getById(Long id);

    /**
     * 广告信息--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<WebsiteCarousel> page(int pageCurrent, int pageSize, WebsiteCarouselExample example);

    /**
     * 广告信息--条件列出
     *
     * @param example 查询条件
     * @return 广告信息列表
     */
    List<WebsiteCarousel> listByExample(WebsiteCarouselExample example);

    /**
     * 广告信息--条件统计
     *
     * @param example 统计条件
     * @return 广告信息数量
     */
    int countByExample(WebsiteCarouselExample example);
}
