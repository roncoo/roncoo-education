package com.roncoo.education.user.service.auth;

import com.roncoo.education.user.service.auth.biz.AuthRegionBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-行政区域表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-行政区域表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/region")
public class AuthRegionController {

    @NotNull
    private final AuthRegionBiz biz;

}
