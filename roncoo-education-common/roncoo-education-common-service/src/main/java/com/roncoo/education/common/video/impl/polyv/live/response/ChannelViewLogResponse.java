package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询频道直播观看详情数据
 *
 * @author LYQ
 */
@Data
public class ChannelViewLogResponse implements Serializable {

    private static final long serialVersionUID = -8093803560438637882L;

    /**
     * 表示此次播放动作的ID
     */
    private String playId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 频道号
     */
    private String channelId;

    /**
     * 播放时长，单位：秒
     */
    private Integer playDuration;

    /**
     * 停留时长，单位：秒
     */
    private Integer stayDuration;

    /**
     * 流量大小，单位：bytes
     */
    private Long flowSize;

    /**
     * 直播的场次ID
     */
    private String sessionId;

    /**
     * 使用POLYV观看页的观众ID
     */
    private String param1;

    /**
     * 使用POLYV观看页的观众昵称
     */
    private String param2;

    /**
     * 观看日志类型，默认为live
     * vod：观看回放
     * live：直播
     */
    private String param3;

    /**
     * POLYV系统参数
     */
    private String param4;

    /**
     * POLYV系统参数
     */
    private String param5;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * ISP运营商
     */
    private String isp;

    /**
     * 播放视频页面地址
     */
    private String referer;

    /**
     * 用户设备
     */
    private String userAgent;

    /**
     * 操作系统
     */
    private String operatingSystem;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 是否为移动端
     */
    private String isMobile;

    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    private String currentDay;

    /**
     * 创建日期 ，13位毫秒级时间戳
     */
    private Long createdTime;

    /**
     * 更新日期 ，13位毫秒级时间戳
     */
    private Long lastModified;

    /**
     * 1：无延迟观看
     * 0：普通观看
     */
    private Integer ptype;
}
