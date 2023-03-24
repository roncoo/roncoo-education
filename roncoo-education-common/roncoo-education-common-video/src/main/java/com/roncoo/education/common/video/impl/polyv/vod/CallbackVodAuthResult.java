/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.video.impl.polyv.vod;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LYQ
 */
@Data
public class CallbackVodAuthResult implements Serializable {

    /**
     * 状态：1正常播放， 2禁止播放
     */
    private Integer status = 2;

    /**
     * 用户名
     */
    private String username = "";

    /**
     * 签名
     */
    private String sign;

    /**
     * 自定义错误提示
     */
    private String msg = "系统繁忙，请重试";

    /**
     * 跑马灯文字字体大小
     */
    private Integer fontSize = 30;

    /**
     * 跑马灯文字字体颜色
     */
    private String fontColor = "0x000000";

    /**
     * 跑马灯文字移动指定像素所需时间
     */
    private Integer speed = 200;

    /**
     * 是否描边
     */
    private String filter = "on";

    /**
     * 跑马灯样式： 1自屏幕右方至左方一直滚动，2屏幕内随机位置闪烁，3自屏幕右方至左方一直滚动，渐隐渐现
     */
    private Integer setting = 2;

    /**
     * 跑马灯文本透明度:0~1
     */
    private Float alpha = 0.5F;

    /**
     * 跑马灯描边透明度:0~1
     */
    private Float filterAlpha = 0.5F;

    /**
     * 跑马灯描边颜色
     */
    private String filterColor = "0x000000";

    /**
     * 跑马灯描边水平模糊量
     */
    private Integer blurX = 0;

    /**
     * 跑马灯描边垂直模糊量
     */
    private Integer blurY = 0;

    /**
     * 跑马灯文本隐藏间隔时间
     */
    private Integer interval = 5;

    /**
     * 跑马灯文本显示时间
     */
    private Integer lifeTime = 5;

    /**
     * 跑马灯文本渐隐渐现时间
     */
    private Integer tweenTime = 3;

    /**
     * 跑马灯描边强度
     */
    private Integer strength = 0;

    /**
     * 是否显示跑马灯，默认为否=off
     */
    private String show = "on";

}
