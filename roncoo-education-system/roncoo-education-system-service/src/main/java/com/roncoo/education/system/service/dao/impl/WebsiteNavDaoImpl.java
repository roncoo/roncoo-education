package com.roncoo.education.system.service.dao.impl;

import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavExample.Criteria;
import com.roncoo.education.system.service.dao.WebsiteNavDao;
import com.roncoo.education.system.service.dao.impl.mapper.WebsiteNavMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WebsiteNavDaoImpl implements WebsiteNavDao {
    @Autowired
    private WebsiteNavMapper websiteNavMapper;

    public int save(WebsiteNav record) {
        record.setId(IdWorker.getId());
        return this.websiteNavMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.websiteNavMapper.deleteByPrimaryKey(id);
    }

    public int updateById(WebsiteNav record) {
        return this.websiteNavMapper.updateByPrimaryKeySelective(record);
    }

    public WebsiteNav getById(Long id) {
        return this.websiteNavMapper.selectByPrimaryKey(id);
    }

    public Page<WebsiteNav> listForPage(int pageCurrent, int pageSize, WebsiteNavExample example) {
        int count = this.websiteNavMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<WebsiteNav>(count, totalPage, pageCurrent, pageSize, this.websiteNavMapper.selectByExample(example));
    }
    
    @Override
	public List<WebsiteNav> listByParentId(Long parentId) {
		WebsiteNavExample example = new WebsiteNavExample();
		Criteria C = example.createCriteria();
		C.andParentIdEqualTo(parentId);
		return this.websiteNavMapper.selectByExample(example);
	}
    
    @Override
	public List<WebsiteNav> listByStatusIdAndParentId(Integer statusId, Long parentId) {
		WebsiteNavExample example = new WebsiteNavExample();
		Criteria C = example.createCriteria();
		C.andStatusIdEqualTo(statusId);
		C.andParentIdEqualTo(parentId);
		example.setOrderByClause("status_id desc, sort desc, id desc");
		return this.websiteNavMapper.selectByExample(example);
	}

}