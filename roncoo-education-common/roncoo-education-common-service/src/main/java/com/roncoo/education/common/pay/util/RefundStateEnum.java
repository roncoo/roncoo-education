package com.roncoo.education.common.pay.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 退款状态
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RefundStateEnum {

    /**
     * 退款成功
     */
    SUCCESS("SUCCESS", "退款成功"),

    /**
     * 退款关闭--商户请求退款后，由于微信侧系统内部原因，导致退款订单异常，此时该订单处于处理中状态，7天后该订单会变为REFUNDCLOSE退款关闭。
     */
    CLOSED("CLOSED", "退款关闭"),

    /**
     * 退款处理中
     */
    PROCESSING("PROCESSING", "退款处理中"),

    /**
     * 退款异常
     */
    ABNORMAL("ABNORMAL", "退款异常");

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;
}
