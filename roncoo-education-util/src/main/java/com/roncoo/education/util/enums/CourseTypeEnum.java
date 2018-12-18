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
public enum CourseTypeEnum {

    COURSE(1, "课程"), CHAPTER(2, "章节"), PERIOD(3, "课时");

    private Integer code;

    private String desc;

}
