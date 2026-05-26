package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI写作类型枚举
 *
 * @author roncoo
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WriteTypeEnum {

    /**
     * 通用写作
     */
    GENERAL(1, "通用写作"),

    /**
     * 课程简介
     */
    COURSE_INTRO(2, "课程简介"),

    /**
     * 文章撰写
     */
    ARTICLE(3, "文章撰写"),

    /**
     * 营销文案
     */
    MARKETING(4, "营销文案"),

    /**
     * 教学大纲
     */
    SYLLABUS(5, "教学大纲");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 根据编码获取枚举
     */
    public static WriteTypeEnum byCode(Integer code) {
        if (code == null) {
            return GENERAL;
        }
        for (WriteTypeEnum value : WriteTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return GENERAL;
    }
}
