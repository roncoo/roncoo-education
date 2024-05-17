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
public enum StudyStatusEnum {

    STUDY(1, "学习中", ""), PAUSE(2, "暂停", "red");

    private Integer code;

    private String desc;

    private String color;

}
