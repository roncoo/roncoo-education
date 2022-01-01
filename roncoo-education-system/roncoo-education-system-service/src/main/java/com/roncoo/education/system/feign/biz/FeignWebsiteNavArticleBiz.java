package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteNavArticleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavArticleExample;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavArticleQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Component
public class FeignWebsiteNavArticleBiz {

    @Autowired
    private WebsiteNavArticleDao dao;

    public Page<WebsiteNavArticleVO> listForPage(WebsiteNavArticleQO qo) {
        WebsiteNavArticleExample example = new WebsiteNavArticleExample();
        example.setOrderByClause(" id desc ");
        Page<WebsiteNavArticle> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, WebsiteNavArticleVO.class);
    }

    public int save(WebsiteNavArticleQO qo) {
        WebsiteNavArticle record = BeanUtil.copyProperties(qo, WebsiteNavArticle.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public WebsiteNavArticleVO getById(Long id) {
        WebsiteNavArticle record = dao.getById(id);
        return BeanUtil.copyProperties(record, WebsiteNavArticleVO.class);
    }

    public int updateById(WebsiteNavArticleQO qo) {
        WebsiteNavArticle record = BeanUtil.copyProperties(qo, WebsiteNavArticle.class);
        return dao.updateById(record);
    }

    public WebsiteNavArticleVO getByNavId(Long navId) {
        WebsiteNavArticle record = dao.getByNavId(navId);
        return BeanUtil.copyProperties(record, WebsiteNavArticleVO.class);
    }

}
