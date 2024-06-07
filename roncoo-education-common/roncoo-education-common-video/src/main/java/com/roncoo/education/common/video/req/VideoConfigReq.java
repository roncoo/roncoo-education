package com.roncoo.education.common.video.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 视频云配置
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class VideoConfigReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 网关域名
     */
    private String websiteDomain;

    /**
     * 点播平台(1:领课云、2:保利威)
     */
    private Integer vodPlatform;

    /**
     * 点播跑马灯(0:关闭、1:开启)
     */
    private Boolean vodEnableMarquee = false;

    /**
     * 直播平台
     */
    private Integer livePlatform;

    /**
     * 直播跑马灯(0:关闭、1:开启)
     */
    private Boolean liveEnableMarquee = false;

    /**
     * 保利威--账号ID
     */
    private String polyvUserId;

    /**
     * 保利威--写入令牌
     */
    private String polyvWriteToken;

    /**
     * 保利威--读取秘钥
     */
    private String polyvReadToken;

    /**
     * 保利威--安全秘钥
     */
    private String polyvSecretKey;

    /**
     * 保利威--应用ID
     */
    private String polyvAppId;

    /**
     * 保利威--应用秘钥
     */
    private String polyvAppSecret;
    /**
     * 保利威--直播域名
     */
    private String polyvLiveDomain = "https://live.polyv.cn/";

    /**
     * 领课云，Url
     */
    private String priyUrl;
    /**
     * 领课云，AccessKeyId
     */
    private String priyAccessKeyId;
    /**
     * 领课云，AccessKeySecret
     */
    private String priyAccessKeySecret;

}
