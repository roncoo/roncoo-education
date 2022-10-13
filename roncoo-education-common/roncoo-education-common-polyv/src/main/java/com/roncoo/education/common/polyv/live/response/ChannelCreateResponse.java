package com.roncoo.education.common.polyv.live.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建单个频道
 *
 * @author LYQ
 */
@Data
public class ChannelCreateResponse implements Serializable {

    private static final long serialVersionUID = -8352472624362765701L;

    /**
     * 频道ID
     */
    private Integer channelId;

    /**
     * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）
     */
    private String userId;
}
