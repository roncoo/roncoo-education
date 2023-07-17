package com.roncoo.education.user.service.api;

import com.roncoo.education.user.service.api.biz.ApiOrderPayBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * API-订单支付信息表
 *
 * @author wujing
 * @date 2022-09-06
 */
@Api(tags = "api-订单支付信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/order/pay")
public class ApiOrderPayController {

    @NotNull
    private final ApiOrderPayBiz biz;

    @ApiOperation(value = "支付通知", notes = "支付回调通知")
    @RequestMapping(value = "/notify/{payModel}/{payImpl}")
    public String notify(HttpServletRequest request, @PathVariable Integer payModel, @PathVariable String payImpl) {
        return biz.notify(request, payModel, payImpl);
    }

}
