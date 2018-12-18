/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否学习枚举类
 * @author forest
 */
@Getter
@AllArgsConstructor
public enum IsStudyEnum {

    YES(1, "已学习"), NO(0, "未学习");

    private Integer code;

    private String desc;

}
