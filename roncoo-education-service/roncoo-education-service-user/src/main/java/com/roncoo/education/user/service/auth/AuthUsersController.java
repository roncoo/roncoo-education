package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthUsersBiz;
import com.roncoo.education.user.service.auth.req.AuthUsersReq;
import com.roncoo.education.user.service.auth.resp.AuthUsersResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * AUTH-用户信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "auth-用户信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/users")
public class AuthUsersController {

    @NotNull
    private final AuthUsersBiz biz;

    /**
     * 用户信息查看接口
     */
    @ApiOperation(value = "查看接口", notes = "获取当前用户的基本信息")
    @GetMapping(value = "/view")
    public Result<AuthUsersResp> view() {
        return biz.view();
    }

    @ApiOperation(value = "更新接口", notes = "更新当前用户的基本信息")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result<String> update(@RequestBody AuthUsersReq req) {
        return biz.update(req);
    }

}
