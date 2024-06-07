package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建单个频道
 *
 * @author LYQ
 */
@Data
public class ChannelRecordGetResponse implements Serializable {

    private static final long serialVersionUID = 8345061104395633165L;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播
     */
    private String userId;

    /**
     * 频道号
     */
    private String channelId;

    /**
     * 开始录制时间，格式为：yyyyMMddHHmmss
     */
    private String startTime;

    /**
     * 结束录制时间，格式为：yyyyMMddHHmmss
     */
    private String endTime;

    /**
     * 录制文件名称
     */
    private String fileName;

    /**
     * 录制文件大小（单位：字节）
     */
    private Long fileSize;

    /**
     * 创建时间，13位毫秒级时间戳
     */
    private Long createdTime;

    /**
     * 视频宽度（像素）
     */
    private Integer width;

    /**
     * 视频高度（像素）
     */
    private Integer height;

    /**
     * 时长（单位：秒）
     */
    private Integer duration;

    /**
     * 录制文件码率（单位：字节）
     */
    private Integer bitrate;

    /**
     * mp4文件地址
     */
    private String mp4;

    /**
     * m3u8文件地址
     */
    private String m3u8;

    /**
     * 直播的场次ID
     */
    private String channelSessionId;

    /**
     * 直播类型  alone：活动直播 ppt：三分屏 topclass：大班课 seminar：研讨会
     */
    private String liveType;
}
