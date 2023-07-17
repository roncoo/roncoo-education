package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 点播平台枚举
 *
 * @author fengyw
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum VodPlatformEnum {

    /**
     * 私有云，支持定制，需付费购买，请联系作者：18302045627
     */
    PRIVATEY(1, "私有云", "priy%"),

    /**
     * 保利威
     */
    POLYV(2, "保利威", "polyv%"),

    /**
     * 百家云
     */
    BAIJY(3, "百家云(待实现)", "baijy%"),

    /**
     * 获得场景
     */
    BOKECC(4, "获得场景(待实现)", "bokecc%");

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
