package com.roncoo.education.common.video.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 点播-播放请求参数
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class VodPlayConfigReq implements Serializable {

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
    private Boolean appWatch = false;
    /**
     * 终端类型（1:pc,2:h5,3:android,4:ios,5:微信小程序）
     */
    private String terminal = "1";
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
    private String extraParams = "HTML5";

    private VodAuthCode vodAuthCode;

    @Data
    public static class VodAuthCode {
        /**
         * 用户ID
         */
        private Long userId;
        /**
         * 课时ID
         */
        private Long periodId;
    }
}
