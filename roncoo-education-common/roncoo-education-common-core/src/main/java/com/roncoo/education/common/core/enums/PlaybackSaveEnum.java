/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum PlaybackSaveEnum {

    SAVE(1, "保存"),
    DISCARD(2, "丢弃");

    private Integer code;

    private String desc;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static PlaybackSaveEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PlaybackSaveEnum val : PlaybackSaveEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }

}
