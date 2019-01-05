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
public enum recruitTypeEnum {

	LECTURER(1, "讲师招募"), AGENT(2, "代理招募");

	private Integer code;

	private String desc;

}
