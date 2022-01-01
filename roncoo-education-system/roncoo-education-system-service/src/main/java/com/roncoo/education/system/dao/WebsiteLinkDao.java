package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLink;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLinkExample;

import java.util.List;

public interface WebsiteLinkDao {
    int save(WebsiteLink record);

    int deleteById(Long id);

    int updateById(WebsiteLink record);

    WebsiteLink getById(Long id);

    Page<WebsiteLink> listForPage(int pageCurrent, int pageSize, WebsiteLinkExample example);

    /**
     * 根据状态查询友情链接集合
     *
     * @param statusId
     * @return
     * @author wuyun
     */
    List<WebsiteLink> listByStatusId(Integer statusId);
}
