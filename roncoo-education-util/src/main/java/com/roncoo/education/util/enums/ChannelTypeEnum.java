/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum ChannelTypeEnum {
	PC(1, "PC端"), APP(2, "APP端"), WEIXIN(3, "微信端"), MANUAL(4, "手工绑定");

	private Integer code;

	private String desc;

}
