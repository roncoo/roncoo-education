package com.roncoo.education.common.polyv.callback;

import lombok.Data;

/**
 * 重制课件成功回调
 *
 * @author LYQ
 */
@Data
public class CallbackLiveCoursewareRefashion {

    /**
     * 频道ID
     */
    private String channelId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 重制对应的回放名称
     */
    private String title;

    /**
     * 重制对应的回放开始时间，格式yyyyMMddHHmmss
     */
    private String startTime;

    /**
     * 时长，单位秒
     */
    private Long duration;

    /**
     * 重制剩余的有效期
     */
    private String remainDay;

    /**
     * 场次ID
     */
    private String sessionId;

    /**
     * 重制视频下载地址，会有防盗链，有效期为24小时
     */
    private String url;

    /**
     * 签名，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙
     */
    private String sign;

    /**
     * 13位的时间戳
     */
    private Long timestamp;

}
