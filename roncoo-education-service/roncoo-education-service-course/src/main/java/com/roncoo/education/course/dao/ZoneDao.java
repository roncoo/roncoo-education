package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;

import java.util.List;

/**
 * 专区 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface ZoneDao {

    /**
     * 保存专区
     *
     * @param record 专区
     * @return 影响记录数
     */
    int save(Zone record);

    /**
     * 根据ID删除专区
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改专区
     *
     * @param record 专区
     * @return 影响记录数
     */
    int updateById(Zone record);

    /**
     * 根据ID获取专区
     *
     * @param id 主键ID
     * @return 专区
     */
    Zone getById(Long id);

    /**
     * 专区--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<Zone> page(int pageCurrent, int pageSize, ZoneExample example);

    /**
     * 专区--条件列出
     *
     * @param example 查询条件
     * @return 专区列表
     */
    List<Zone> listByExample(ZoneExample example);

    /**
     * 专区--条件统计
     *
     * @param example 统计条件
     * @return 专区数量
     */
    int countByExample(ZoneExample example);
}
