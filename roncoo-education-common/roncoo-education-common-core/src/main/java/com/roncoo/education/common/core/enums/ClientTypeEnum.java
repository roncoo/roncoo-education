package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客戶端类型(1:PC、2:H5、3:小程序、4:APP)
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClientTypeEnum {

    /**
     * PC
     */
    PC(1, "pc", "PC"),

    /**
     * H5
     */
    H5(2, "h5", "H5"),

    /**
     * 小程序
     */
    MP(3, "mini_app", "小程序"),

    /**
     * app
     */
    APP(4, "app", "APP");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 标识
     */
    private final String sign;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 根据编码获取客户端类型
     *
     * @param code 编码
     * @return 客户端小程序
     */
    public static ClientTypeEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (ClientTypeEnum typeEnum : ClientTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }

}
