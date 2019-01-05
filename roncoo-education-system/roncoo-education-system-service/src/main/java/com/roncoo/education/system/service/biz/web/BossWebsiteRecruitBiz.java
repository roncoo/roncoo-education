package com.roncoo.education.system.service.biz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.WebsiteRecruitQO;
import com.roncoo.education.system.common.bean.vo.WebsiteRecruitVO;
import com.roncoo.education.system.service.dao.WebsiteRecruitDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruit;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruitExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.ArrayListUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 招募中心
 *
 * @author YZJ
 */
@Component
public class BossWebsiteRecruitBiz {

	@Autowired
	private WebsiteRecruitDao dao;

	public Page<WebsiteRecruitVO> listForPage(WebsiteRecruitQO qo) {
		WebsiteRecruitExample example = new WebsiteRecruitExample();
		example.setOrderByClause(" id desc ");
		Page<WebsiteRecruit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, WebsiteRecruitVO.class);
	}

	public int save(WebsiteRecruitQO qo) {
		WebsiteRecruit record = BeanUtil.copyProperties(qo, WebsiteRecruit.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public WebsiteRecruitVO getById(Long id) {
		WebsiteRecruit record = dao.getById(id);
		return BeanUtil.copyProperties(record, WebsiteRecruitVO.class);
	}

	public int updateById(WebsiteRecruitQO qo) {
		WebsiteRecruit record = BeanUtil.copyProperties(qo, WebsiteRecruit.class);
		return dao.updateById(record);
	}

	public List<WebsiteRecruitVO> listWebsiteRecruit() {
		List<WebsiteRecruit> list = dao.listWebsiteRecruit();
		return ArrayListUtil.copy(list, WebsiteRecruitVO.class);
	}

}
