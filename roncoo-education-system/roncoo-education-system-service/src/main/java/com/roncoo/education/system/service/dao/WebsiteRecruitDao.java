package com.roncoo.education.system.service.dao;

import java.util.List;

import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruit;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruitExample;
import com.roncoo.education.util.base.Page;

public interface WebsiteRecruitDao {
	int save(WebsiteRecruit record);

	int deleteById(Long id);

	int updateById(WebsiteRecruit record);

	WebsiteRecruit getById(Long id);

	Page<WebsiteRecruit> listForPage(int pageCurrent, int pageSize, WebsiteRecruitExample example);

	/**
	 * 获取招募集合信息
	 * 
	 * @return
	 * @author YZJ
	 */
	List<WebsiteRecruit> listWebsiteRecruit();

	/**
	 * 根据招募分类获取招募信息
	 * 
	 * @param recruitType
	 * @return
	 * @author YZJ
	 */
	WebsiteRecruit getByRecruitType(Integer recruitType);
}