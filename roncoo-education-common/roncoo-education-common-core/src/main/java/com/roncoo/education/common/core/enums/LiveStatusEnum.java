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
public enum LiveStatusEnum {

    WAITING(1, "待开播"),
    LIVING(2, "直播中"),
    COMPLETION(3, "已结束"),
    PLAYBACK(4, "可回放");

    private Integer code;

    private String desc;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static LiveStatusEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LiveStatusEnum val : LiveStatusEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }

}
