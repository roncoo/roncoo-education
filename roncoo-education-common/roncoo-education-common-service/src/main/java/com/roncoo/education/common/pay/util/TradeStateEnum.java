package com.roncoo.education.common.pay.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 交易状态
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TradeStateEnum {

    /**
     * 支付成功
     */
    SUCCESS("SUCCESS", "支付成功"),

    /**
     * 转入退款
     */
    REFUND("REFUND", "转入退款"),

    /**
     * 未支付
     */
    NOT_PAY("NOTPAY", "未支付"),

    /**
     * 已关闭
     */
    CLOSED("CLOSED", "已关闭"),

    /**
     * 已撤销（仅付款码支付会返回）
     */
    REVOKED("REVOKED", "已撤销"),

    /**
     * 用户支付中（仅付款码支付会返回）
     */
    USER_PAYING("USERPAYING", "用户支付中"),

    /**
     * 支付失败（仅付款码支付会返回）
     */
    PAY_ERROR("PAYERROR", "支付失败");

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;
}
