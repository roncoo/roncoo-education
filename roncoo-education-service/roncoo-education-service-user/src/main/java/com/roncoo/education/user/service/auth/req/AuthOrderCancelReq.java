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
@Schema(description = "API-AUTH-订单取消支付")
public class AuthOrderCancelReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "订单编号", required = true)
    private Long orderNo;
}
