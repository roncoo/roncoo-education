package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;

import java.util.List;

/**
 * 系统配置 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface SysConfigDao {

    /**
     * 保存系统配置
     *
     * @param record 系统配置
     * @return 影响记录数
     */
    int save(SysConfig record);

    /**
     * 根据ID删除系统配置
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改系统配置
     *
     * @param record 系统配置
     * @return 影响记录数
     */
    int updateById(SysConfig record);

    /**
     * 根据ID获取系统配置
     *
     * @param id 主键ID
     * @return 系统配置
     */
    SysConfig getById(Long id);

    /**
     * 系统配置--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<SysConfig> page(int pageCurrent, int pageSize, SysConfigExample example);

    /**
     * 系统配置--条件列出
     *
     * @param example 查询条件
     * @return 系统配置列表
     */
    List<SysConfig> listByExample(SysConfigExample example);

    /**
     * 系统配置--条件统计
     *
     * @param example 统计条件
     * @return 系统配置数量
     */
    int countByExample(SysConfigExample example);

    SysConfig getByConfigKey(String configKey);
}
