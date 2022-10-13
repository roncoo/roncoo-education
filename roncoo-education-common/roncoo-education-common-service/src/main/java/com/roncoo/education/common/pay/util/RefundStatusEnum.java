package com.roncoo.education.common.pay.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 退款状态(1:退款中、2:退款成功、3:退款失败、4:未知)
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RefundStatusEnum {

    /**
     * 退款中
     */
    PROCESSING(1, "退款中"),

    /**
     * 退款成功
     */
    SUCCESS(2, "退款成功"),

    /**
     * 退款失败
     */
    FAILURE(3, "退款失败"),

    /**
     * 未知
     */
    UNKNOWN(4, "未知");

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
    public static RefundStatusEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (RefundStatusEnum value : RefundStatusEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
