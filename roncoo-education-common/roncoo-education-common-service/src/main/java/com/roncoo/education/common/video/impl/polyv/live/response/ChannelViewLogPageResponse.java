package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询频道直播观看详情数据
 *
 * @author LYQ
 */
@Data
public class ChannelViewLogPageResponse implements Serializable {

    private static final long serialVersionUID = 3136794762024111985L;

    /**
     * 每页显示的数据条数，默认每页显示1000条数据
     */
    private Integer pageSize;

    /**
     * 当前的页数
     */
    private Integer pageNumber;

    /**
     * 总的条数
     */
    private Integer totalItems;

    /**
     * 查询的结果列表
     */
    private List<LogContent> contents;

    /**
     * 当前页第一条记录在总结果集中的位置
     */
    private Integer startRow;

    /**
     * 是否为第一页，值为：true/false
     */
    private Boolean firstPage;

    /**
     * 是否为最后一页，值为：true/false
     */
    private Boolean lastPage;

    /**
     * 上一页编号
     */
    private Integer prePageNumber;

    /**
     * 下一页编号
     */
    private Integer nextPageNumber;

    /**
     * 每页数量大小
     */
    private Integer limit;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 当前页最后一条记录在总结果集中的位置
     */
    private Integer endRow;

    /**
     * 分页起始记录
     */
    private Integer offset;

    @Data
    public static class LogContent {

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
         * 直播场次ID
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
         * 观看日志类型，默认为live  vod：观看回放  live：直播
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
         * 日志创建日期，13位毫秒级时间戳
         */
        private Long createdTime;

        /**
         * 日志更新日期，13位毫秒级时间戳
         */
        private Long lastModified;

        /**
         * 1：无延迟观看  0：普通观看
         */
        private String ptype;
    }
}
