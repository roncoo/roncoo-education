package com.roncoo.education.common.video.impl.polyv.vod;

import lombok.Data;

/**
 * @author LYQ
 */
@Data
public class CallbackVodAuth {

    /**
     * 视频VID
     */
    private String vid;

    /**
     * 时间戳
     */
    private String t;

    /**
     * 播放code值，包含自定义参数json字符串加密
     */
    private String code;

    /**
     * 由于H5播放器通过Ajax方式请求用户接口，需要跨域请求，因此需要callback参数
     */
    private String callback;
}
