package com.roncoo.education.system.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteArticleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticle;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticleExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticleExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticleEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticlePageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticleSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteArticlePageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteArticleViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 站点导航文章
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignWebsiteArticleBiz extends BaseBiz {

    @NotNull
    private final WebsiteArticleDao dao;

    public Page
            <WebsiteArticlePageVO> page(WebsiteArticlePageQO qo) {
        WebsiteArticleExample example = new WebsiteArticleExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<WebsiteArticle> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, WebsiteArticlePageVO.class);
    }

    public int save(WebsiteArticleSaveQO qo) {
        WebsiteArticle record = BeanUtil.copyProperties(qo, WebsiteArticle.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(WebsiteArticleEditQO qo) {
        WebsiteArticle record = BeanUtil.copyProperties(qo, WebsiteArticle.class);
        return dao.updateById(record);
    }

    public WebsiteArticleViewVO getById(Long id) {
        WebsiteArticle record = dao.getById(id);
        return BeanUtil.copyProperties(record, WebsiteArticleViewVO.class);
    }
}
