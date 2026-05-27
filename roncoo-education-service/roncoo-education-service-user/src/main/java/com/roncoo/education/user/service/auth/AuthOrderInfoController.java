package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthOrderInfoBiz;
import com.roncoo.education.user.service.auth.req.AuthOrderInfoReq;
import com.roncoo.education.user.service.auth.resp.AuthOrderInfoResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

/**
 * AUTH-订单信息表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Tag(name = "auth-订单信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/order/info")
public class AuthOrderInfoController {

    @NotNull
    private final AuthOrderInfoBiz biz;

    @Operation(summary = "分页接口", description = "根据条件分页列出订单信息")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<Page<AuthOrderInfoResp>> listForPage(@RequestBody AuthOrderInfoReq req) {
        return biz.listForPage(req);
    }

    /**
     * 用户信息查看接口
     */
    @Operation(summary = "订单查看", description = "获取信订单信息")
    @GetMapping(value = "/view")
    public Result<AuthOrderInfoResp> view(@RequestParam Long orderNo) {
        return biz.view(orderNo);
    }

}
