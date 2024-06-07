/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 直播模板
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LiveModelEnum {

    /**
     * 文档+视频(三分屏)
     */
    PPT(1, "ppt");

    private final Integer code;

    private final String desc;

    public static LiveModelEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LiveModelEnum modelEnum : LiveModelEnum.values()) {
            if (modelEnum.getCode().equals(code)) {
                return modelEnum;
            }
        }
        return null;
    }

}
