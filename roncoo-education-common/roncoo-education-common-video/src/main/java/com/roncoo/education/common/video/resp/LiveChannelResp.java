package com.roncoo.education.common.video.resp;

import com.roncoo.education.common.core.enums.LiveModelEnum;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class LiveChannelResp implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 频道号
     */
    private String channelId;
    /**
     * 频道密码
     */
    private String channelPwd;

    /**
     * 直播模板
     */
    private LiveModelEnum liveModel;
}
