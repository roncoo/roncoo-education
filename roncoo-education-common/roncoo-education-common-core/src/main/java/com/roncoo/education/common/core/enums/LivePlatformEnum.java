package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 直播平台
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LivePlatformEnum {
    /**
     * 保利威
     */
    POLYV(1, "保利威"),

    /**
     * 百家云
     */
    BJYUN(2, "百家云");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 根据编码获取直播类型
     *
     * @param code 编码
     * @return 直播类型枚举
     */
    public static LivePlatformEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }

        LivePlatformEnum[] enums = LivePlatformEnum.values();
        for (LivePlatformEnum typeEnum : enums) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}
