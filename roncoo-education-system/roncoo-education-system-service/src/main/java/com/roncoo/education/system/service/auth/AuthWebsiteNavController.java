package com.roncoo.education.system.service.auth;

import com.roncoo.education.system.service.auth.biz.AuthWebsiteNavBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-头部导航
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-头部导航")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/auth/website/nav")
public class AuthWebsiteNavController {

    @NotNull
    private final AuthWebsiteNavBiz biz;

}
