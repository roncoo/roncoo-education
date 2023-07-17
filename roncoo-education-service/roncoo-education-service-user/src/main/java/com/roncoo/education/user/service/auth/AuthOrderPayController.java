package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthOrderPayBiz;
import com.roncoo.education.user.service.auth.req.AuthOrderCancelReq;
import com.roncoo.education.user.service.auth.req.AuthOrderCountinuePayReq;
import com.roncoo.education.user.service.auth.req.AuthOrderPayReq;
import com.roncoo.education.user.service.auth.resp.AuthOrderPayResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-订单支付
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "auth-订单支付")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/order/pay")
public class AuthOrderPayController {

    @NotNull
    private final AuthOrderPayBiz biz;

    @ApiOperation(value = "支付接口", notes = "课程下单接口")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Result<AuthOrderPayResp> pay(@RequestBody AuthOrderPayReq req) {
        return biz.pay(req);
    }

    @ApiOperation(value = "支付接口", notes = "课程下单接口")
    @RequestMapping(value = "/continue", method = RequestMethod.POST)
    public Result<AuthOrderPayResp> continuePay(@RequestBody AuthOrderCountinuePayReq req) {
        return biz.continuePay(req);
    }

    @ApiOperation(value = "取消支付", notes = "取消订单支付")
    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    public Result<String> cancel(@RequestBody AuthOrderCancelReq req) {
        return biz.cancel(req);
    }

}
