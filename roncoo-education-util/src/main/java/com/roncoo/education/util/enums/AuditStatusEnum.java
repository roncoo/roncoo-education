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
public enum AuditStatusEnum {

	WAIT(0, "待审核",""),
	//
	SUCCESS(1, "审核通过","green"),
	//
	FAIL(2, "审核不通过","red");

	private Integer code;
	
	private String desc;
	
	private String color;
}
