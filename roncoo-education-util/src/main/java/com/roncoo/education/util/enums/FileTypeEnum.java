/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import com.roncoo.education.util.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

	ALIYUN(1, "阿里云"), FDSF(2, "FastDFS"), LOCAL(3, "本地");

	private Integer code;

	private String desc;

}
