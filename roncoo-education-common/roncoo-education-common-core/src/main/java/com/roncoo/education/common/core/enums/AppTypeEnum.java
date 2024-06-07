/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fengyw
 */
@Getter
@AllArgsConstructor
public enum AppTypeEnum {

    ANDROID(1, "Android"), IOS(2, "IOS");

    private Integer code;

    private String desc;
}
