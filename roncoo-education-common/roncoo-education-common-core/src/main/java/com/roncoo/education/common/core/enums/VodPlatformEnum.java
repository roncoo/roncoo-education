package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 点播平台枚举
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum VodPlatformEnum {

    /**
     * 私有云
     */
    PRIVATE(1, "私有云(商业版提供)", "pri%"),

    /**
     * 保利威
     */
    POLYV(2, "保利威", "polyv%"),

    /**
     * 百家云
     */
    BJYUN(3, "百家云(待实现)", "baijy%");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 标记
     */
    private final String tag;

    /**
     * 根据编码获取点播平台枚举
     *
     * @param code 编码
     * @return 点播平台枚举
     */
    public static VodPlatformEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (VodPlatformEnum value : VodPlatformEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
