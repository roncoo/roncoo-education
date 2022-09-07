package com.roncoo.education.common.polyv.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传类型
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UploadTypeEnum {

    /**
     * 素材
     */
    MATERIAL(1, "素材"),

    /**
     * 回放转存任务
     */
    TRANSITION_TASK(2, "回放转存任务");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
}
