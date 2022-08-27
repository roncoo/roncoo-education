package com.roncoo.education.common.pay.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款查询参数
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class RefundQueryResp implements Serializable {

    private static final long serialVersionUID = -1199317493664710610L;

    /**
     * 是否发起成功--具体结果通过查询或者通知确定
     */
    private boolean success;

    /**
     * 退款返回信息
     */
    private String msg;

    /**
     * 退款订单号
     */
    private String refundOrderNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 退款状态(1:退款中、2:退款成功、3:退款失败、4:未知)
     */
    private Integer refundStatus;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 成功时间
     */
    private Date successTime;
}
