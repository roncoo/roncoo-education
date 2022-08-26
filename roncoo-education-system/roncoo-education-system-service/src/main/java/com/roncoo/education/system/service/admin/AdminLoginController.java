package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminLoginBiz;
import com.roncoo.education.system.service.admin.req.AdminUsersLoginReq;
import com.roncoo.education.system.service.admin.resp.AdminUsersLoginResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengyw
 */
@Api(tags = "登录接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/login")
public class AdminLoginController {

    private final AdminLoginBiz biz;

    @ApiOperation(value = "密码登录", notes = "用户使用密码登录")
    @PostMapping(value = "/password")
    public Result<AdminUsersLoginResp> login(@RequestBody AdminUsersLoginReq req) {
        return biz.login(req);
    }

}
