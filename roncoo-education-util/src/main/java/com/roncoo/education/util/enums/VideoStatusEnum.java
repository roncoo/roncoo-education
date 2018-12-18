package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 视频上传状态
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum VideoStatusEnum {

	WAIT(1, "待上传", ""), SUCCES(2, "上传成功", "green"),FINAL(3, "上传失败", "red");
	
	private Integer code;

	private String desc;
	
	private String color;

}
