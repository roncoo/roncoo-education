package com.roncoo.education.user.service.api;

import com.roncoo.education.user.service.api.biz.ApiOrderInfoBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-订单信息表
 *
 * @author wujing
 * @date 2022-09-06
 */
@Api(tags = "API-订单信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/order/info")
public class ApiOrderInfoController {

    @NotNull
    private final ApiOrderInfoBiz biz;

}
