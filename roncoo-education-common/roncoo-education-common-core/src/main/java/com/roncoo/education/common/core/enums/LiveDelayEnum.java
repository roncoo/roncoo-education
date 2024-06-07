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
public enum LiveDelayEnum {

    RTMP(1, "正常延迟"), WEBRTC(2, "无延迟");

    private Integer code;

    private String desc;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static LiveDelayEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LiveDelayEnum val : LiveDelayEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }

}
