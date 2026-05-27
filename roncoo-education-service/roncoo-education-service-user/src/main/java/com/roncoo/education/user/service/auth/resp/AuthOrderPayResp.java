package com.roncoo.education.user.service.auth.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Schema(description = "API-AUTH-订单支付信息表")
public class AuthOrderPayResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "支付参数")
    private String payMessage;

    @Schema(description = "订单编号")
    private Long orderNo;

    @Schema(description = "订单状态")
    private Integer orderStatus;

    @Schema(description = "流水号")
    private Long serialNumber;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "划线价")
    private BigDecimal rulingPrice;

    @Schema(description = "课程价格")
    private BigDecimal coursePrice;

    @Schema(description = "支付方式")
    private Integer payType;

}
