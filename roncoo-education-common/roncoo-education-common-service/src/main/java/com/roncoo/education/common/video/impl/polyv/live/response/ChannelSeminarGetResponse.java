package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 研讨会参会条件
 *
 * @author KYH
 */
@Data
public class ChannelSeminarGetResponse implements Serializable {

    private static final long serialVersionUID = -8352472624362765701L;

    /**
     * 频道ID
     */
    private Integer channelId;

    /**
     * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）
     */
    private String userId;

    /**
     * 用于实现一个频道设置两个参会条件，为1或2（1为主要条件，2为次要条件）
     */
    private Integer rank;

    /**
     * 是否开启参会条件
     * Y：开启
     * N：不开启
     */
    private String enabled;

    /**
     * 参会条件类型
     * password：密码登录
     * direct：独立授权
     */
    private String authType;

    /**
     * 角色
     * host：主持人
     * attendee：参会人
     */
    private String roleCode;

    /**
     * 密码登录的password
     */
    private String password;

    /**
     * 独立授权key
     */
    private String directKey;
}
