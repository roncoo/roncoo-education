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
public enum PeriodTypeEnum {

    RESOURCE(10, "资源"),
    LIVE(20, "直播");

    private Integer code;

    private String desc;

}
