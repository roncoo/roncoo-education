/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.polyv.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 直播状态
 *
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum LiveStatEnum {

    /**
     * 正在直播
     */
    NOW("live", "正在直播"),

    /**
     * 直播结束
     */
    END("end", "直播结束");

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

}
