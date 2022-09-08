package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 视频上传状态
 *
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum VideoStatusEnum {

    WAIT(1, "转码中", ""), SUCCES(2, "成功", "green"), FINAL(3, "失败", "red");

    private Integer code;

    private String desc;

    private String color;

}
