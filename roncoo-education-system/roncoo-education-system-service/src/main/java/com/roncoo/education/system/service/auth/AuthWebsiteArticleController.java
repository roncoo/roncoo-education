package com.roncoo.education.system.service.auth;

import com.roncoo.education.system.service.auth.biz.AuthWebsiteArticleBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-站点导航文章
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-站点导航文章")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/auth/website/article")
public class AuthWebsiteArticleController {

    @NotNull
    private final AuthWebsiteArticleBiz biz;

}
