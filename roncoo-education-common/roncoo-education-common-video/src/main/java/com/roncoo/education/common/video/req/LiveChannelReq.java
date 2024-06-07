package com.roncoo.education.common.video.req;

import com.roncoo.education.common.core.enums.LiveModelEnum;
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
public class LiveChannelReq implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 直播名称
     */
    private String liveName;

    /**
     * 直播场景
     */
    private LiveSceneEnum liveScene;

    /**
     * 直播模板
     */
    private LiveModelEnum liveModel;

    /**
     * 直播延迟
     */
    private Boolean webrtc;

    /**
     * 连麦人数限制
     */
    private Integer linkMicLimit;

}
