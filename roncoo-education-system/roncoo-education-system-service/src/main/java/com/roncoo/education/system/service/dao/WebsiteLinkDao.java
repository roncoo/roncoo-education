package com.roncoo.education.system.service.dao;

import java.util.List;

import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteLink;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteLinkExample;
import com.roncoo.education.util.base.Page;

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