package com.roncoo.education.common.pay.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付模式(1:直连模式、2:服务商模式)
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PayModelEnum {

    /**
     * 直连模式
     */
    DIRECT_SALES(1, "直连模式"),

    /**
     * 服务商模式
     */
    SERVICE_PROVIDER(2, "服务商模式");

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
    public static PayModelEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (PayModelEnum val : PayModelEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }
}
