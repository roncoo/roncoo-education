package com.roncoo.education.system.service.dao.impl;

import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavArticleExample.Criteria;
import com.roncoo.education.system.service.dao.WebsiteNavArticleDao;
import com.roncoo.education.system.service.dao.impl.mapper.WebsiteNavArticleMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavArticleExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WebsiteNavArticleDaoImpl implements WebsiteNavArticleDao {
    @Autowired
    private WebsiteNavArticleMapper websiteNavArticleMapper;

    public int save(WebsiteNavArticle record) {
        record.setId(IdWorker.getId());
        return this.websiteNavArticleMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.websiteNavArticleMapper.deleteByPrimaryKey(id);
    }

    public int updateById(WebsiteNavArticle record) {
        return this.websiteNavArticleMapper.updateByPrimaryKeySelective(record);
    }

    public WebsiteNavArticle getById(Long id) {
        return this.websiteNavArticleMapper.selectByPrimaryKey(id);
    }

    public Page<WebsiteNavArticle> listForPage(int pageCurrent, int pageSize, WebsiteNavArticleExample example) {
        int count = this.websiteNavArticleMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<WebsiteNavArticle>(count, totalPage, pageCurrent, pageSize, this.websiteNavArticleMapper.selectByExample(example));
    }
    
    @Override
	public WebsiteNavArticle getByNavId(Long navId) {
		WebsiteNavArticleExample example = new WebsiteNavArticleExample();
		example.createCriteria().andNavIdEqualTo(navId);
		List<WebsiteNavArticle> list = this.websiteNavArticleMapper.selectByExampleWithBLOBs(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
    
    @Override
	public WebsiteNavArticle getByNavIdAndStatusId(Long navId, Integer statusId) {
		WebsiteNavArticleExample example = new WebsiteNavArticleExample();
		Criteria C = example.createCriteria();
		C.andNavIdEqualTo(navId);
		C.andStatusIdEqualTo(statusId);
		List<WebsiteNavArticle> list = this.websiteNavArticleMapper.selectByExampleWithBLOBs(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}