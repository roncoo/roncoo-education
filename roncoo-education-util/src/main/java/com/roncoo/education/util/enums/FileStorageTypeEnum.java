/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author YZJ
 */
@Getter
@AllArgsConstructor
public enum FileStorageTypeEnum {

	DOC(1, "附件"), PICTURE(2, "图片"), VIDEO(3, "视频");

	private Integer code;

	private String desc;

}
