package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticle;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticleExample;

import java.util.List;

public interface WebsiteArticleDao {
    int save(WebsiteArticle record);

    int deleteById(Long id);

    int updateById(WebsiteArticle record);

    WebsiteArticle getById(Long id);

    Page<WebsiteArticle> listForPage(int pageCurrent, int pageSize, WebsiteArticleExample example);

    /**
     * 根据状态获取集合
     *
     * @param statusId
     * @return
     * @author wuyun
     */
    List<WebsiteArticle> listByStatusId(Integer statusId);
}
