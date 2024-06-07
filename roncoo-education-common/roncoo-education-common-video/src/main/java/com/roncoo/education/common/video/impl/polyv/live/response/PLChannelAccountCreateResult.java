package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

/**
 * 创建频道的助教或嘉宾角色请求响应对象类
 */
@Data
public class PLChannelAccountCreateResult {

    /**
     * <pre>
     * 字段名：助教/嘉宾账号
     * 类型： String
     * 描述：助教/嘉宾账号
     * </pre>
     */
    protected String account;
    /**
     * <pre>
     * 字段名：用户ID
     * 类型： String
     * 描述：用户ID
     * </pre>
     */
    protected String userId;
    /**
     * <pre>
     * 字段名：频道号
     * 类型： String
     * 描述：频道号
     * </pre>
     */
    protected String channelId;
    /**
     * <pre>
     * 字段名：角色密码
     * 类型： String
     * 描述：频道号
     * </pre>
     */
    protected String passwd;
    /**
     * <pre>
     * 字段名：角色名称
     * 类型： String
     * 描述：频道号
     * </pre>
     */
    protected String nickname;
    /**
     * <pre>
     * 字段名：角色流名，单独使用无效
     * 类型： String
     * 描述：角色流名
     * </pre>
     */
    protected String stream;
    /**
     * <pre>
     * 字段名：角色状态(Y：开启, N：关闭)
     * 类型： String
     * 描述：角色状态(Y：开启, N：关闭)
     * </pre>
     */
    protected String status;

}
