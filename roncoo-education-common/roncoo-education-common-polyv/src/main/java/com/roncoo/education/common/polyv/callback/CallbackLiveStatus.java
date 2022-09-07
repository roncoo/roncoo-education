package com.roncoo.education.common.polyv.callback;

import lombok.Data;

/**
 * 直播状态改变回调
 *
 * @author LYQ
 */
@Data
public class CallbackLiveStatus {

    /**
     * 频道ID
     */
    private String channelId;

    /**
     * 直播频道的状态：live正在直播，end直播结束
     */
    private String status;

    /**
     * 13位的时间戳
     */
    private Long timestamp;

    /**
     * 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙
     */
    private String sign;

    /**
     * 直播的场次ID
     */
    private String sessionId;

    /**
     * 直播的开始时间,13位的时间戳
     */
    private Long startTime;

    /**
     * 直播的结束时间(当status=end的时候有值，status=live的时候为空值),13位的时间戳
     */
    private Long endTime;
}
