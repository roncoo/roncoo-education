/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.polyv.callback;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LYQ
 */
@Data
public class CallbackVodAuthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺ID
     */
    private Long sid;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称--跑马灯显示内容
     */
    private String showText;

     /**
     * 观看类型(1：课程观看)--枚举：VodWatchTypeEnum
     */
    private Integer authWatchType;

    /**
     *来源类型 （1:用户 2:店铺 3:平台）
     */
    private Integer sourceType;


    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 章节ID
     */
    private Long chapterId;
    /**
     * 课时ID
     */
    private Long periodId;

    /**
     * 资源ID
     */
    private Long resourceId;
}
