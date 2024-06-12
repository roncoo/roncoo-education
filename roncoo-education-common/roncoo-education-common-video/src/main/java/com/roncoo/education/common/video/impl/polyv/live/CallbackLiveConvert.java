package com.roncoo.education.common.video.impl.polyv.live;

import lombok.Data;

/**
 * 批量转存录制视频到云点播回调
 *
 * @author LYQ
 */
@Data
public class CallbackLiveConvert {

    /**
     * 频道ID
     */
    private String channelId;

    /**
     * 转存成功的视频ID
     */
    private String vid;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频时长，格式为，hh:mm:ss
     */
    private String duration;

    /**
     * 视频文件大小，单位为byte
     */
    private Long fileSize;

    /**
     * 13位的时间戳
     */
    private Long timestamp;

    /**
     * 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙
     */
    private String sign;

    /**
     * 录制的场次和时间对应的数组字符串，格式：["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"] ，其中："20190703145126,4,fdqbopvtnv" 第一个字段是开始时间，第二个字段是直播的时长，第三个是对应的sessionId
     */
    private String sessionIds;

    /**
     * 转存对应的录制文件ID
     */
    private String fileId;

    /**
     * 转存对应的录制文件ID
     */
    private String videoId;

    /**
     * 转存的录制来源
     * manual：云录制
     * auto：自动录制
     * merge：合并
     * clip：裁剪
     * remake：重制课件
     */
    private String origin;

    /**
     * 回放对应的单个场次ID
     */
    private String sessionId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 转存成功返回success
     */
    private String status;

    /**
     * 错误码
     * userExpired：用户已过期
     * spaceOverSize：点播空间不足
     * unknown：未知异常
     */
    private String code;
}
