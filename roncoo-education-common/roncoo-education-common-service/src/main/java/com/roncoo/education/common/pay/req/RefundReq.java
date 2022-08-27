package com.roncoo.education.common.pay.req;

import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.WxPayConfig;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 交易退款
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class RefundReq implements Serializable {

    private static final long serialVersionUID = -6224197557669469200L;

    /**
     * 退款订单号
     */
    private String refundOrderNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 交易号--支付通道的订单号
     */
    private String tradeNo;

    /**
     * 订单金额（单位：元）
     */
    private BigDecimal amount;

    /**
     * 退款金额（单位：元）
     */
    private BigDecimal refundAmount;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 支付模式
     */
    private Integer payModel;

    /**
     * 支付宝支付配置
     */
    private AliPayConfig aliPayConfig;

    /**
     * 微信支付配置
     */
    private WxPayConfig wxPayConfig;
}
