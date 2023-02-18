/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.polyv.vod;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LYQ
 */
@Data
public class CallbackVodAuthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 跑马灯显示内容
     */
    private String showText;
    /**
     * 课时ID
     */
    private Long periodId;
}
