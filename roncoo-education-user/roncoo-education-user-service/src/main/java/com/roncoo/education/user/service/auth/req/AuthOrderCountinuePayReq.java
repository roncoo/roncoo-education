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
@ApiModel(value = "AuthOrderCountinuePayReq", description = "API-AUTH-订单继续支付")
public class AuthOrderCountinuePayReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号", required = true)
    private Long orderNo;

    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付", required = true)
    private Integer payType;

    @ApiModelProperty(value = "购买渠道：1web", required = false)
    private Integer channelType = 1;

    @ApiModelProperty(value = "用户备注", required = false)
    private String remarkCus;
}
