package com.roncoo.education.system.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.system.service.dao.WebsiteRecruitDao;
import com.roncoo.education.system.service.dao.impl.mapper.WebsiteRecruitMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruit;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruitExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruitExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class WebsiteRecruitDaoImpl implements WebsiteRecruitDao {
	@Autowired
	private WebsiteRecruitMapper websiteRecruitMapper;

	@Override
	public int save(WebsiteRecruit record) {
		record.setId(IdWorker.getId());
		return this.websiteRecruitMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.websiteRecruitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(WebsiteRecruit record) {
		return this.websiteRecruitMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public WebsiteRecruit getById(Long id) {
		return this.websiteRecruitMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<WebsiteRecruit> listForPage(int pageCurrent, int pageSize, WebsiteRecruitExample example) {
		int count = this.websiteRecruitMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<WebsiteRecruit>(count, totalPage, pageCurrent, pageSize, this.websiteRecruitMapper.selectByExample(example));
	}

	@Override
	public List<WebsiteRecruit> listWebsiteRecruit() {
		WebsiteRecruitExample example = new WebsiteRecruitExample();
		List<WebsiteRecruit> list = this.websiteRecruitMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public WebsiteRecruit getByRecruitType(Integer recruitType) {
		WebsiteRecruitExample example = new WebsiteRecruitExample();
		Criteria c = example.createCriteria();
		c.andRecruitTypeEqualTo(recruitType);
		List<WebsiteRecruit> list = this.websiteRecruitMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}