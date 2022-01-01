package com.roncoo.education.system.service.api.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteNavArticleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.system.service.api.bo.WebsiteNavArticleBO;
import com.roncoo.education.system.service.api.dto.WebsiteNavArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteNavArticleBiz {

    @Autowired
    private WebsiteNavArticleDao dao;

    public Result<WebsiteNavArticleDTO> get(WebsiteNavArticleBO websiteNavArticleBO) {
        if (StringUtils.isEmpty(websiteNavArticleBO.getNavId())) {
            return Result.error("navId不能为空");
        }
        WebsiteNavArticle websiteNavArticle = dao.getByNavIdAndStatusId(websiteNavArticleBO.getNavId(), StatusIdEnum.YES.getCode());
        if (ObjectUtil.isNull(websiteNavArticle)) {
            return Result.error("没有找到站点导航文章信息");
        }
        return Result.success(BeanUtil.copyProperties(websiteNavArticle, WebsiteNavArticleDTO.class));
    }
}
