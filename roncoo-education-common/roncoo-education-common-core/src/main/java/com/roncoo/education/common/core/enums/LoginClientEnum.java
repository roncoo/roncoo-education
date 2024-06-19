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
public enum LoginClientEnum {

    PC(1, "电脑端"),
    H5(21, "H5端"),
    MP(31, "小程序端"),
    ANDROID(41, "安卓端"),
    IOS(51, "苹果端");

    private Integer code;

    private String desc;

}
