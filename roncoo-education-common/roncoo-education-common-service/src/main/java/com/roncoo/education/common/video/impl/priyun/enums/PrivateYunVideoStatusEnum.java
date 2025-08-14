package com.roncoo.education.common.video.impl.priyun.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 视频状态
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PrivateYunVideoStatusEnum {

    /**
     * 上传中
     */
    UPLOADING(1, "上传中"),

    /**
     * 上传失败
     */
    UPLOAD_FAIL(2, "上传失败"),

    /**
     * 转码中
     */
    TRANSCODING(3, "转码中"),

    /**
     * 转码失败
     */
    TRANSCODE_FAIL(4, "转码失败"),

    /**
     * 转码完成
     */
    COMPLETE(5, "完成");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
}
