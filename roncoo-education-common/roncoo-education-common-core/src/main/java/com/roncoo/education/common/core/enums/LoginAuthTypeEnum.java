package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录授权类型
 *
 * @author fengyw
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LoginAuthTypeEnum {

    /**
     * 微信网页应用
     */
    PC(1, "网页应用"),

    /**
     * 微信公众号
     */
    MP(2, "公众号"),

    /**
     * 微信小程序端
     */
    MA(3, "小程序");

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
    public static LoginAuthTypeEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (LoginAuthTypeEnum val : LoginAuthTypeEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }
}
