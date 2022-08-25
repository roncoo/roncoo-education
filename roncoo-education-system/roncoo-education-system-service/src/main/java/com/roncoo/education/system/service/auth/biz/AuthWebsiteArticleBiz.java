package com.roncoo.education.system.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteArticleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-站点导航文章
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthWebsiteArticleBiz extends BaseBiz {

    @NotNull
    private final WebsiteArticleDao dao;

}
