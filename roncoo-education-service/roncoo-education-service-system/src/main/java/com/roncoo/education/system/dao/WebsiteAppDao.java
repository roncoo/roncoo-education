package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteApp;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAppExample;

import java.util.List;

/**
 * app版本管理 服务类
 *
 * @author fengyw
 * @date 2024-06-07
 */
public interface WebsiteAppDao {

    /**
     * 保存app版本管理
     *
     * @param record app版本管理
     * @return 影响记录数
     */
    int save(WebsiteApp record);

    /**
     * 根据ID删除app版本管理
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改app版本管理
     *
     * @param record app版本管理
     * @return 影响记录数
     */
    int updateById(WebsiteApp record);

    /**
     * 根据ID获取app版本管理
     *
     * @param id 主键ID
     * @return app版本管理
     */
    WebsiteApp getById(Long id);

    /**
     * app版本管理--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<WebsiteApp> page(int pageCurrent, int pageSize, WebsiteAppExample example);

    /**
     * app版本管理--条件列出
     *
     * @param example 查询条件
     * @return app版本管理列表
     */
    List<WebsiteApp> listByExample(WebsiteAppExample example);

    /**
     * app版本管理--条件统计
     *
     * @param example 统计条件
     * @return app版本管理数量
     */
    int countByExample(WebsiteAppExample example);
}
