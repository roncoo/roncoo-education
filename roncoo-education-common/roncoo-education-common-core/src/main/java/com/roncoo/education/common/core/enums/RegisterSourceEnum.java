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
public enum RegisterSourceEnum {

    SYS_PC(11, "PC端注册"),
    SYS_H5(12, "H5端注册"),
    SYS_PC_WX(13, "微信网页应用"),
    WX_MP(21, "公众号"),
    WX_MA(22, "小程序"),
    APP_ANDROID(32, "安卓"),
    APP_IOS(33, "苹果");

    private Integer code;

    private String desc;

}
