package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.Website;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteExample;

public interface WebsiteDao {
    int save(Website record);

    int deleteById(Long id);

    int updateById(Website record);

    Website getById(Long id);

    Page<Website> listForPage(int pageCurrent, int pageSize, WebsiteExample example);

    /**
     * 获取站点信息
     *
     * @author wuyun
     */
    Website getWebsite();

    /**
     * 根据状态信息查找站点信息
     *
     * @param statusId
     * @return
     * @author wuyun
     */
    Website getByStatusId(Integer statusId);
}
