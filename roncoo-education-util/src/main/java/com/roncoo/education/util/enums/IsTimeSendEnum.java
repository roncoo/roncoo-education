/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否定时发送
 * 
 * @author wuyun
 *
 */
@Getter
@AllArgsConstructor
public enum IsTimeSendEnum {

	YES(1, "定时发送"), NO(0, "不定时发送");

	private Integer code;

	private String desc;

}
