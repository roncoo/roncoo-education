package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountInfoResponse implements Serializable {

    private static final long serialVersionUID = 8710459367564655440L;

    /**
     * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）
     */
    private String userId;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 最大频道数
     */
    private Integer maxChannels;

    /**
     * 总共频道数
     */
    private Integer totalChannels;

    /**
     * 有效频道数
     */
    private Integer availableChannels;
}
