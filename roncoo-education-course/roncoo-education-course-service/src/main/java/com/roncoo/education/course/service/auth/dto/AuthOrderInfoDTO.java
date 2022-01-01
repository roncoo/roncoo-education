package com.roncoo.education.course.service.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "下单时间", required = true)
    private Date gmtCreate;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderNo;
    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实际支付金额", required = true)
    private BigDecimal pricePaid;
    /**
     * 支付方式：1微信支付，2支付宝支付
     */
    @ApiModelProperty(value = "支付方式(1:微信支付，2:支付宝支付)", required = true)
    private Integer payType;
    /**
     * 订单状态：0待支付，1成功支付，2支付失败，3已关闭，4已退款
     */
    @ApiModelProperty(value = "订单状态(1:待支付，2:支付成功，3:支付失败，4:已关闭)", required = true)
    private Integer orderStatus;
    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间", required = true)
    private Date payTime;
}
