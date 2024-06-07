package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询账号可用直播分钟数
 *
 * @author LYQ
 */
@Data
public class AccountUserDurationResponse implements Serializable {

    private static final long serialVersionUID = 4306261784410326614L;

    /**
     * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）
     */
    private String userId;

    /**
     * 当前可用的分钟数
     */
    private Long available;

    /**
     * 历史已经使用的分钟数
     */
    private Long used;
}
