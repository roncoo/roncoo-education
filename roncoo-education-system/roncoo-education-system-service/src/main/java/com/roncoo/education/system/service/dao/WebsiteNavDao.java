package com.roncoo.education.system.service.dao;

import java.util.List;

import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavExample;
import com.roncoo.education.util.base.Page;

public interface WebsiteNavDao {
	int save(WebsiteNav record);

	int deleteById(Long id);

	int updateById(WebsiteNav record);

	WebsiteNav getById(Long id);

	Page<WebsiteNav> listForPage(int pageCurrent, int pageSize, WebsiteNavExample example);

	/**
	 * 根据父类ID查找站点导航信息
	 * 
	 * @param parentId
	 * @return
	 * @author wuyun
	 */
	List<WebsiteNav> listByParentId(Long id);

	/**
	 * 根据状态和父ID信息查找站点导航信息
	 * 
	 * @param statusId
	 * @param parentId
	 * @author wuyun
	 */
	List<WebsiteNav> listByStatusIdAndParentId(Integer statusId, Long parentId);
}