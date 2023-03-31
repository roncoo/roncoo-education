package com.roncoo.education.user.service.auth.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "API-AUTH-订单支付信息表")
public class AuthOrderPayResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付参数")
    private String payMessage;

    @ApiModelProperty(value = "订单编号")
    private Long orderNo;

    @ApiModelProperty(value = "流水号")
    private Long serialNumber;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "划线价")
    private BigDecimal rulingPrice;

    @ApiModelProperty(value = "课程价格")
    private BigDecimal coursePrice;

    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单")
    private Integer payType;

}
