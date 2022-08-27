package com.roncoo.education.common.pay.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易下单通知
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class TradeNotifyResp implements Serializable {

    private static final long serialVersionUID = 1195869049655301491L;

    /**
     * 是否请求成功
     */
    private boolean success;

    /**
     * 请求响应信息
     */
    private String msg;

    /**
     * 交易状态(1:待支付、2:支付成功、3:支付失败、4:未知)
     */
    private Integer tradeStatus;

    /**
     * 交易订单号
     */
    private String tradeOrderNo;

    /**
     * 交易号--支付通道的订单号
     */
    private String tradeNo;

    /**
     * 附加信息
     */
    private String attach;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 成功时间
     */
    private Date successTime;

    /**
     * 返回信息
     */
    private String returnMsg;
}
