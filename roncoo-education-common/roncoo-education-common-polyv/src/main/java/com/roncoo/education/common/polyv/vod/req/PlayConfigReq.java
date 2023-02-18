package com.roncoo.education.common.polyv.vod.req;

import com.roncoo.education.common.polyv.vod.CallbackVodAuthCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询视频信息
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PlayConfigReq implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 视频vid
     */
    private String vid;
    /**
     * 过期时间，单位：秒。如果传0则表示不过期，默认为0
     */
    private Long expiresIn = 30L;
    /**
     * 是否为移动端
     */
    private Boolean appWatch;
    /**
     * 终端类型（1:pc,2:h5,3:android,4:ios,5:微信小程序）
     */
    private String terminal;
    /**
     * 观看者ID
     */
    private String viewerId;
    /**
     * 观看者IP
     */
    private String viewerIp;
    /**
     * 观看者名称
     */
    private String viewerName;
    /**
     * 透传字段
     */
    private String extraParams;

    private CallbackVodAuthCode callbackVodAuthCode;

    public PlayConfigReq(String vid, Boolean appWatch, String terminal, String viewerId, String viewerIp, String viewerName, CallbackVodAuthCode callbackVodAuthCode) {
        this.vid = vid;
        this.appWatch = appWatch;
        this.terminal = terminal;
        this.viewerId = viewerId;
        this.viewerIp = viewerIp;
        this.viewerName = viewerName;
        this.callbackVodAuthCode = callbackVodAuthCode;
    }
}
