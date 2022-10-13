package com.roncoo.education.common.pay.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 交易状态(1:待支付、2:支付成功、3:支付失败、4:订单关闭、5:未知)
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TradeStatusEnum {

    /**
     * 待支付
     */
    WAIT_PAY(1, "待支付"),

    /**
     * 支付成功
     */
    SUCCESS(2, "支付成功"),

    /**
     * 支付失败
     */
    FAILURE(3, "支付失败");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static TradeStatusEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (TradeStatusEnum value : TradeStatusEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
