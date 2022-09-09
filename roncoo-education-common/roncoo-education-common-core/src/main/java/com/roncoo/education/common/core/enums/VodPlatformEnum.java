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
     * 保利威
     */
    POLYV(1, "保利威", "polyv%"),

    /**
     * 百家云
     */
    BJYUN(2, "百家云", "baijy%"),

    /**
     * 私有云
     */
    PRIVATE(3, "私有云", "pri%");

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
