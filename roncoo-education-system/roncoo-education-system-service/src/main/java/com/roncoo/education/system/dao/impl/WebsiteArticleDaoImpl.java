package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.WebsiteArticleDao;
import com.roncoo.education.system.dao.impl.mapper.WebsiteArticleMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticle;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebsiteArticleDaoImpl implements WebsiteArticleDao {
    @Autowired
    private WebsiteArticleMapper WebsiteArticleMapper;

    @Override
    public int save(WebsiteArticle record) {
        record.setId(IdWorker.getId());
        return this.WebsiteArticleMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.WebsiteArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(WebsiteArticle record) {
        return this.WebsiteArticleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public WebsiteArticle getById(Long id) {
        return this.WebsiteArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<WebsiteArticle> listForPage(int pageCurrent, int pageSize, WebsiteArticleExample example) {
        int count = this.WebsiteArticleMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<WebsiteArticle>(count, totalPage, pageCurrent, pageSize, this.WebsiteArticleMapper.selectByExample(example));
    }

    @Override
    public List<WebsiteArticle> listByStatusId(Integer statusId) {
        WebsiteArticleExample example = new WebsiteArticleExample();
        example.createCriteria().andStatusIdEqualTo(statusId);
        example.setOrderByClause(" sort desc, id desc ");
        return this.WebsiteArticleMapper.selectByExampleWithBLOBs(example);
    }
}
