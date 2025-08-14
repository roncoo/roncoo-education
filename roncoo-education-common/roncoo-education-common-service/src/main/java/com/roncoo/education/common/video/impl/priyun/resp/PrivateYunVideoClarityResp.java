package com.roncoo.education.common.video.impl.priyun.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频清晰度播放地址
 * </p>
 *
 * @author zhandehuang
 * @version 1.0
 * @date 2022/5/27 0027
 */
@Data
@Accessors(chain = true)
public class PrivateYunVideoClarityResp {
    private static final long serialVersionUID = 1L;

    private String sdUrl;

    private String hdUrl;
}
