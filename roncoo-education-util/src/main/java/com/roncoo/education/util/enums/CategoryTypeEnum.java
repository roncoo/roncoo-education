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
public enum CategoryTypeEnum {

    COURSE(1, "课程分类"), RESOURCE(2, "资源分类");

    private Integer code;

    private String desc;

}
