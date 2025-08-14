package com.roncoo.education.common.video.impl.polyv.vod;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 回调时间类型
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CallbackEventTypeEnum {

    /**
     * 视频上传完成--点播服务端接收完上传的视频文件后，会产生视频上传完成事件
     * 字段：type
     */
    UPLOAD("upload", "GET", "视频上传完成"),

    /**
     * 视频上传完成--不合规格视频（当上传的视频的信息无法被系统分析时，判断为不合规格视频）
     * 字段：type
     */
    INVALID_VIDEO("invalidVideo", "GET", "不合规格视频"),


    /**
     * 异步上传处理失败--当使用管理后台的视频同步功能或者使用异步方式上传视频，处理失败时会产生此事件
     * <p>
     * 当收到此回调时，说明远程上传视频失败，需检查源视频的URL地址。
     * 字段：type
     */
    ASYNC_UPLOAD("async_upload", "GET", "异步上传处理失败"),


    /**
     * 视频同步失败--当视频上传成功后，系统需要把上传至边缘节点的视频源文件同步回中心节点，同步失败时会产生此事件。
     * 字段：type
     */
    VIDEO_SYNCED_FAIL("videoSyncedFail", "GET", "视频同步失败"),


    /**
     * 视频单个清晰度转码完成，转码完成--视频的某种格式的某个清晰度（如：高清的mp4格式）转码完成时会产生此事件
     * 视频任一清晰度转码完成并审核通过后即可播放（视频状态会更新为“已发布”，如果其它清晰度的转码还未完成，播放器会自动选择已完成转码的清晰度）
     * 字段：type
     */
    ENCODE("encode", "GET", "转码完成"),


    /**
     * 视频单个清晰度转码完成，转码失败--视频的某种格式的某个清晰度（如：高清的mp4格式）转码完成时会产生此事件
     * 视频任一清晰度转码完成并审核通过后即可播放（视频状态会更新为“已发布”，如果其它清晰度的转码还未完成，播放器会自动选择已完成转码的清晰度）
     * 字段：type
     */
    ENCODE_FAILED("encode_failed", "GET", "转码失败"),


    /**
     * 视频所有清晰度转码完成会产生此事件
     * 字段：eventType
     */
    TRANSCODE_COMPLETE("TranscodeComplete", "POST", "视频全部清晰度转码完成"),

    /**
     * 视频审核完成，审核通过--视频任一清晰度转码完成后需经过智能审核或人工审核，只有审核通过的视频才可播放。审核完成后会产生此事件
     * 字段：type
     */
    PASS("pass", "GET", "审核通过"),

    /**
     * 视频审核完成，审核不通过--视频任一清晰度转码完成后需经过智能审核或人工审核，只有审核通过的视频才可播放。审核完成后会产生此事件
     * 字段：type
     */
    NO_PASS("nopass ", "GET", "审核不通过"),

    /**
     * 视频删除完成--当视频文件被删除至回收站或者从回收站彻底删除时，会产生此事件
     * 字段：eventType
     */
    DELETE_MEDIA_COMPLETE("DeleteMediaComplete", "POST", "视频删除完成"),

    /**
     * 视频内容安全审核不通过--当视频经过AI智能扫描，发现有确认或疑似违规内容时，会产生此事件
     * 字段：eventType
     */
    AI_CONTENT_SCAN_NOT_PASSED("AIContentScanNotPassed", "POST", "视频删除完成"),

    /**
     * 课件转换完成--当上传的课件（PPT、PDF文件）转换图片完成时，会产生此事件
     * 字段：eventType
     */
    COURSEWARE_CONVERT_COMPLETE("CoursewareConvertComplete", "POST", "视频删除完成");

    /**
     * 编码
     */
    private final String code;

    /**
     * 请求方式
     */
    private final String requestType;

    /**
     * 描述
     */
    private final String desc;
}
