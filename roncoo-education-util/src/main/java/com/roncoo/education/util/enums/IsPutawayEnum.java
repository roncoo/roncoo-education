/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum IsPutawayEnum {

	YES(1, "上架", ""), NO(0, "下架", "red");
	
	private Integer code;

    private String desc;
    
    private String color;
}
