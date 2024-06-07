/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 直播场景枚举
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LiveSceneEnum {

    /**
     * 大班课
     */
    LARGE_CLASS(1, "大班课"),

    /**
     * 小班课
     */
    SMALL_CLASS(2, "小班课"),

    /**
     * 研讨会（该场景没有直播模板）
     */
    SEMINAR(3, "研讨会");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;


    /**
     * 根据编码获取枚举
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static LiveSceneEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LiveSceneEnum value : LiveSceneEnum.values()) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        return null;
    }

}
