package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample;

import java.util.List;

public interface WebsiteNavDao {
    int save(WebsiteNav record);

    int deleteById(Long id);

    int updateById(WebsiteNav record);

    WebsiteNav getById(Long id);

    Page<WebsiteNav> page(int pageCurrent, int pageSize, WebsiteNavExample example);

    /**
     * 根据状态获取集合
     *
     * @param statusId
     * @return
     */
    List<WebsiteNav> listByStatusId(Integer statusId);
}
