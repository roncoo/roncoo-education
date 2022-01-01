package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.WebsiteLinkDao;
import com.roncoo.education.system.dao.impl.mapper.WebsiteLinkMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLink;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLinkExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLinkExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebsiteLinkDaoImpl implements WebsiteLinkDao {
    @Autowired
    private WebsiteLinkMapper websiteLinkMapper;

    public int save(WebsiteLink record) {
        record.setId(IdWorker.getId());
        return this.websiteLinkMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.websiteLinkMapper.deleteByPrimaryKey(id);
    }

    public int updateById(WebsiteLink record) {
        return this.websiteLinkMapper.updateByPrimaryKeySelective(record);
    }

    public WebsiteLink getById(Long id) {
        return this.websiteLinkMapper.selectByPrimaryKey(id);
    }

    public Page<WebsiteLink> listForPage(int pageCurrent, int pageSize, WebsiteLinkExample example) {
        int count = this.websiteLinkMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<WebsiteLink>(count, totalPage, pageCurrent, pageSize, this.websiteLinkMapper.selectByExample(example));
    }

    @Override
    public List<WebsiteLink> listByStatusId(Integer statusId) {
        WebsiteLinkExample example = new WebsiteLinkExample();
        Criteria c = example.createCriteria();
        c.andStatusIdEqualTo(statusId);
        example.setOrderByClause(" sort desc, id desc ");
        return this.websiteLinkMapper.selectByExample(example);
    }

}
