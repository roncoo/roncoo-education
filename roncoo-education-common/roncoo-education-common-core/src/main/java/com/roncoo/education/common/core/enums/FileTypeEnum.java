/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author YZJ
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    VIDEO(1, "视频"), FILE(2, "文件");

    private Integer code;

    private String desc;

}
