package com.roncoo.education.common.polyv.live.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询频道直播观看详情数据
 *
 * @author LYQ
 */
@Data
public class ChannelViewLogPageRequest implements Serializable {

    private static final long serialVersionUID = -8487419901288777128L;

    /**
     * 当前页码，默认为1
     */
    private Integer page;

    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    private String currentDay;

    /**
     * 每页显示的数据条数，默认每页显示1000条数据
     */
    private Integer pageSize;

    /**
     * 查询开始时间，为13位毫秒级时间戳
     */
    private Long startTime;

    /**
     * 查询结束时间，13位毫秒级时间戳
     */
    private Long endTime;

    /**
     * 观看用户ID，默认查询全部
     */
    private String param1;

    /**
     * 观看用户昵称，默认查询全部
     */
    private String param2;

    /**
     * 观看日志类型，默认查询全部 vod：观看回放 live：直播
     */
    private String param3;
}
