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
public enum VideoTypeEnum {

	POLYV(1, "保利威视"), QINIU(2, "七牛");

	private Integer code;

	private String desc;

}
