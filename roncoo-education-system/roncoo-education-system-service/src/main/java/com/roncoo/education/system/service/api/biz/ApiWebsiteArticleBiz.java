package com.roncoo.education.system.service.api.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteArticleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticle;
import com.roncoo.education.system.service.api.req.ApiWebsiteArticleReq;
import com.roncoo.education.system.service.api.resp.ApiWebsiteArticleResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Component
@CacheConfig(cacheNames = {"system"})
public class ApiWebsiteArticleBiz {

    @Autowired
    private WebsiteArticleDao websiteArticleDao;

    @Cacheable
    public Result<List<ApiWebsiteArticleResp>> list() {
        List<WebsiteArticle> result = websiteArticleDao.listByStatusId(StatusIdEnum.YES.getCode());
        return Result.success(PageUtil.copyList(result, ApiWebsiteArticleResp.class));
    }

    @Cacheable
    public Result<ApiWebsiteArticleResp> get(ApiWebsiteArticleReq websiteArticleReq) {
        if (ObjectUtil.isNull(websiteArticleReq.getId())) {
            return Result.error("ID不能为空");
        }
        WebsiteArticle websiteArticle = websiteArticleDao.getById(websiteArticleReq.getId());
        return Result.success(BeanUtil.copyProperties(websiteArticle, ApiWebsiteArticleResp.class));
    }

}
