package com.roncoo.education.common.video.req;

import com.roncoo.education.common.core.enums.LiveSceneEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 开播信息
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LiveWatchReq implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 频道号
     */
    private String channelId;
    /**
     * 是否为移动端
     */
    private Boolean appWatch = false;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;

    private LiveSceneEnum liveScene = LiveSceneEnum.LARGE_CLASS;
}
