package com.roncoo.education.common.polyv.callback;

import lombok.Data;

/**
 * 直播回放生成回调
 *
 * @author LYQ
 */
@Data
public class CallbackLivePlayback {

    /**
     * 频道ID
     */
    private String channelId;

    /**
     * 录制文件地址
     */
    private String fileUrl;

    /**
     * 文件类型，m3u8或者mp4
     */
    private String format;

    /**
     * 13位的时间戳
     */
    private Long timestamp;

    /**
     * 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙
     */
    private String sign;

    /**
     * 录制唯一的id
     */
    private String fileId;

    /**
     * 录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪
     */
    private String origin;

    /**
     * （该字段只对开启云录制功能有用），值为 'Y'，表示该场直播录制同时存在云录制和自动录制，值为"N"，该场直播只有自动录制
     */
    private String hasRtcRecord;

    /**
     * 场次ID
     */
    private String sessionId;

}
