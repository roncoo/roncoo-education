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
@ApiModel(value = "AuthOrderPayReq", description = "API-AUTH-订单支付信息表")
public class AuthOrderPayReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单")
    private Integer payType = 2;

    @ApiModelProperty(value = "购买渠道：1web", required = false)
    private Integer channelType = 1;

    @ApiModelProperty(value = "用户备注", required = false)
    private String remarkCus;
}
