package com.roncoo.education.user.service.api;

import com.roncoo.education.user.service.api.biz.ApiOrderPayBiz;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * API-订单支付信息表
 *
 * @author wujing
 * @date 2022-09-06
 */
@Tag(name = "api-订单支付信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/order/pay")
public class ApiOrderPayController {

    @NotNull
    private final ApiOrderPayBiz biz;

    @Operation(summary = "支付通知", description = "支付回调通知")
    @RequestMapping(value = "/notify/{payModel}/{payImpl}")
    public String notify(HttpServletRequest request, @PathVariable Integer payModel, @PathVariable String payImpl) {
        return biz.notify(request, payModel, payImpl);
    }

}
