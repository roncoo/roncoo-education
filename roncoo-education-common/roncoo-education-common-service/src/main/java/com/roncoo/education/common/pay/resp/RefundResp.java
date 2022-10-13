package com.roncoo.education.common.pay.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 交易退款
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class RefundResp implements Serializable {

    private static final long serialVersionUID = -2580314205917652871L;

    /**
     * 是否发起成功--具体结果通过查询或者通知确定
     */
    private boolean success;

    /**
     * 退款返回信息
     */
    private String msg;

    /**
     * 退款状态(1:退款中、2:退款成功、3:退款失败、4:未知)
     */
    private Integer refundStatus;
}
