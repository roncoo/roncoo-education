package com.roncoo.education.system.service.feign.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.feign.qo.WebsiteQO;
import com.roncoo.education.system.feign.vo.WebsiteVO;
import com.roncoo.education.system.service.dao.WebsiteDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Website;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Component
public class FeignWebsiteBiz {

	@Autowired
	private WebsiteDao dao;

	public Page<WebsiteVO> listForPage(WebsiteQO qo) {
		WebsiteExample example = new WebsiteExample();
		example.setOrderByClause(" id desc ");
		Page<Website> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, WebsiteVO.class);
	}

	public int save(WebsiteQO qo) {
		Website record = BeanUtil.copyProperties(qo, Website.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public WebsiteVO getById(Long id) {
		Website record = dao.getById(id);
		return BeanUtil.copyProperties(record, WebsiteVO.class);
	}

	public int updateById(WebsiteQO qo) {
		Website record = BeanUtil.copyProperties(qo, Website.class);
		return dao.updateById(record);
	}

	public WebsiteVO getWebsite() {
		Website website = dao.getWebsite();
		return BeanUtil.copyProperties(website, WebsiteVO.class);
	}

}
