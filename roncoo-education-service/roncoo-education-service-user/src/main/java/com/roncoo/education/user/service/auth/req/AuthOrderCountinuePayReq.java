package com.roncoo.education.user.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "API-AUTH-订单继续支付")
public class AuthOrderCountinuePayReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单编号", required = true)
    private Long orderNo;

    @Schema(description = "支付方式：1微信支付，2支付宝支付", required = true)
    private Integer payType;

    @Schema(description = "用户终端IP")
    private String userClientIp;

    @Schema(description = "用户付款中途退出的回调地址")
    private String quitUrl;

    @Schema(description = "OpenID")
    private String openId;
}
