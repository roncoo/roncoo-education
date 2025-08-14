package com.roncoo.education.common.video.req;

import com.roncoo.education.common.core.enums.LiveSceneEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 开播信息
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LiveBroadcastReq implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 直播场景，默认为大班课
     */
    private LiveSceneEnum liveScene = LiveSceneEnum.LARGE_CLASS;
    /**
     * 是否开启跑马灯
     */
    private Boolean enableMarquee;
    /**
     * 跑马灯类型
     */
    private Integer marqueeTypeEnum;
    /**
     * 跑马灯内容
     */
    private String marqueeContent;
    /**
     * 是否开启水印
     */
    private Boolean enableWatermark;
    /**
     * 水印内容
     */
    private String watermarkContent;
    /**
     * 直播名称
     */
    private String liveName;
    /**
     * 直播介绍
     */
    private String liveDesc;
    /**
     * 连麦人数限制
     */
    private Integer linkMicLimit = 16;
    /**
     * 是否开启无延迟，默认不开启
     */
    private Boolean webrtc = false;
    /**
     * 频道号
     */
    private String channelId;
    /**
     * 频道密码
     */
    private String channelPwd;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 研讨会使用，role=host为主持人, role=attendee为参会人
     */
    private String role = "host";
    /**
     * 外部链接
     */
    private String externalUrl;
    /**
     * 助教(0:老师、1:助教)
     */
    private Boolean assistant = false;
}
