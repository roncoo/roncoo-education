package com.roncoo.education.common.video.impl.polyv.live.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改管理员身份信息
 */
@Data
public class ChannelUpdateTeacherRequest implements Serializable {

    private static final long serialVersionUID = 8358132334891912486L;

    /**
     * 讲师昵称
     */
    private String nickname;

    /**
     * 讲师头衔
     */
    private String actor;

    /**
     * 频道密码
     */
    private String passwd;

    /**
     * 头像图片地址，如果为空，则使用默认头像
     */
    private String avatar;
}
