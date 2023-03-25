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
public class VideoConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 网关域名
     */
    private String websiteDomain;

    /**
     * 点播平台(1:私有云、2:保利威、3:百家云)
     */
    private Integer vodPlatform;

    /**
     * 点播跑马灯(0:关闭、1:开启)
     */
    private Boolean vodEnableMarquee = false;

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
     * 私有云，Url
     */
    private String priyUrl;
    /**
     * 私有云，AccessKeyId
     */
    private String priyAccessKeyId;
    /**
     * 私有云，AccessKeySecret
     */
    private String priyAccessKeySecret;
}
