package com.roncoo.education.common.polyv.live.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 批量查询频道状态
 */
@Data
public class ChannelLiveStatusResponse implements Serializable {

    private static final long serialVersionUID = -4658172987267401350L;

    /**
     * 频道号
     */
    private String channelId;

    /**
     * 频道的直播状态  live：正在直播 end：未直播
     */
    private String status;

    /**
     * 研讨会频道的研讨状态 seminar_live：正在研讨 seminar_end：暂无研讨
     */
    private String seminarStatus;
}
