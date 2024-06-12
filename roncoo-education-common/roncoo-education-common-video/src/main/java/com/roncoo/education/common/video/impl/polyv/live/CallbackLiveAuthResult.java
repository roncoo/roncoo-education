package com.roncoo.education.common.video.impl.polyv.live;

import lombok.Data;

/**
 * 外部授权回调
 *
 * @author LYQ
 * @see <a href="https://help.polyv.net/index.html#/live/api/web/watch_condition/external_authorization_2">外部授权</a>
 */
@Data
public class CallbackLiveAuthResult {

    /**
     * 请求结果，1表示成功，0表示失败
     */
    private Integer status = 1;

    /**
     * 用户ID
     */
    private String userid;

    /**
     * 观众昵称
     */
    private String nickname;

    /**
     * 自定义跑马灯字段
     */
    private String marqueeName;

    /**
     * 观众头像地址
     */
    private String avatar;
    /**
     * 请求失败时观看页跳转的地址（会带上channelId和userid）
     */
    private String errorUrl;
}
