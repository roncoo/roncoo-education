package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminLoginBiz;
import com.roncoo.education.system.service.admin.req.AdminSysUserLoginReq;
import com.roncoo.education.system.service.admin.resp.AdminSysUserLoginResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengyw
 */
@Tag(name = "admin-登录接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/login")
public class AdminLoginController {

    private final AdminLoginBiz biz;

    @Operation(summary = "密码登录", description = "用户使用密码登录")
    @PostMapping(value = "/password")
    public Result<AdminSysUserLoginResp> login(@RequestBody @Valid AdminSysUserLoginReq req) {
        return biz.login(req);
    }

}
