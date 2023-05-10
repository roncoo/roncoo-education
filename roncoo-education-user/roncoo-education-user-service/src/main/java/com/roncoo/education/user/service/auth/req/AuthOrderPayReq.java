package com.roncoo.education.user.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-订单支付信息表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-订单支付信息表")
public class AuthOrderPayReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "支付方式：1微信扫码支付(默认)，2支付宝扫码支付")
    private Integer payType = 1;

    @ApiModelProperty(value = "用户终端IP")
    private String userClientIp;

    @ApiModelProperty(value = "用户付款中途退出的回调地址")
    private String quitUrl;

    @ApiModelProperty(value = "用户备注", required = false)
    private String remarkCus;

}
