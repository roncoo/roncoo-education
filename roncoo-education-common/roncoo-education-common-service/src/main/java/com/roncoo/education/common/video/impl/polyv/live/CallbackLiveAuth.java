package com.roncoo.education.common.video.impl.polyv.live;

import lombok.Data;

/**
 * 外部授权回调
 *
 * @author LYQ
 * @see <a href="https://help.polyv.net/index.html#/live/api/web/watch_condition/external_authorization_2">外部授权</a>
 */
@Data
public class CallbackLiveAuth {

    /**
     * 用户ID
     */
    private String userid;

    /**
     * 频道号
     */
    private String channelId;

    /**
     * 当前时间的毫秒级时间戳
     */
    private Long ts;

    /**
     * 授权令牌
     */
    private String token;
}
