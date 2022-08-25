package com.roncoo.education.system.service.auth;

import com.roncoo.education.system.service.auth.biz.AuthWebsiteAdvBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-广告信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-广告信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/auth/website/adv")
public class AuthWebsiteAdvController {

    @NotNull
    private final AuthWebsiteAdvBiz biz;

}
