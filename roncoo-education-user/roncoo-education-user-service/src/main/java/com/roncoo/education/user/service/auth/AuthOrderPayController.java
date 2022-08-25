package com.roncoo.education.user.service.auth;

import com.roncoo.education.user.service.auth.biz.AuthOrderPayBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-订单支付信息表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-订单支付信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/order/pay")
public class AuthOrderPayController {

    @NotNull
    private final AuthOrderPayBiz biz;

}
