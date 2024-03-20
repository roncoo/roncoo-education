/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    VIDEO(1, "视频"), AUDIO(2, "音频"), DOC(3, "文档"), PIC(4, "图片"), ZIP(5, "压缩包");

    private Integer code;

    private String desc;

}
