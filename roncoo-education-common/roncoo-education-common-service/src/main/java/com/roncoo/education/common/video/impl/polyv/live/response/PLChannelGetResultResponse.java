package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

/**
 * 直播频道信息获取请求响应对象类
 */
@Data
public class PLChannelGetResultResponse {

    /**
     * <pre>
     * 字段名：appId
     * 是否必填：是
     * 类型：String
     * 描述：从API设置中获取，在直播系统登记的appId
     * </pre>
     */
    protected String appId;

    /**
     * <pre>
     * 字段名：直播账号ID
     * 是否必填：是
     * 类型：String(64)
     * 描述：直播账号ID
     * </pre>
     */
    protected String userId;
    /**
     * <pre>
     * 字段名：AppSecret（应用密匙）
     * 是否必填：是
     * 类型：String
     * </pre>
     */
    protected String appSecret;

    /**
     * <pre>
     * 字段名：频道号
     * 变量名：channelId
     * 类型：int(11)
     * </pre>
     */
    protected int channelId;

    /**
     * <pre>
     * 字段名：频道名称
     * 变量名：name
     * 类型：String(64)
     * </pre>
     */
    protected String name;

    /**
     * <pre>
     * 字段名：频道描述
     * 变量名：description
     * 类型：String
     * </pre>
     */
    protected String description;

    /**
     * <pre>
     * 字段名：直播推流地址
     * 变量名：url
     * 类型：String
     * </pre>
     */
    protected String url;

    /**
     * <pre>
     * 字段名：直播流名称
     * 变量名：stream
     * 类型：String
     * </pre>
     */
    protected String stream;

    /**
     * <pre>
     * 字段名：LOGO图片
     * 变量名：logoImage
     * 类型：String
     * </pre>
     */
    protected String logoImage;

    /**
     * <pre>
     * 字段名：LOGO透明度
     * 变量名：logoOpacity （1表示完全不透明）
     * 类型：Int
     * </pre>
     */
    protected int logoOpacity;

    /**
     * <pre>
     * 字段名：LOGO位置
     * 变量名：logoPosition
     * 类型：String
     * </pre>
     */
    protected String logoPosition;

    /**
     * <pre>
     * 字段名：Logo的跳转链接
     * 变量名：logoHref
     * 类型：String
     * </pre>
     */
    protected String logoHref;

    /**
     * <pre>
     * 字段名：播放前显示的封面图
     * 变量名：coverImage
     * 类型：String
     * </pre>
     */
    protected String coverImage;

    /**
     * <pre>
     * 字段名：封面图的跳转链接
     * 变量名：coverHref
     * 类型：String
     * </pre>
     */
    protected String coverHref;

    /**
     * <pre>
     * 字段名：等待推流时的显示图片
     * 变量名：waitImage
     * 类型：String
     * </pre>
     */
    protected String waitImage;

    /**
     * <pre>
     * 字段名：等待推流时显示图片的跳转链接
     * 变量名：waitHref
     * 类型：String
     * </pre>
     */
    protected String waitHref;

    /**
     * <pre>
     * 字段名：切断流时的显示图片
     * 变量名：cutoffImage
     * 类型：String
     * </pre>
     */
    protected String cutoffImage;

    /**
     * <pre>
     * 字段名：切断流时显示图片的跳转链接
     * 变量名：cutoffHref
     * 类型：String
     * </pre>
     */
    protected String cutoffHref;

    /**
     * <pre>
     * 字段名：广告类型
     * 变量名：advertType
     * 类型：String
     * </pre>
     */
    protected String advertType;

    /**
     * <pre>
     * 字段名：广告时长
     * 变量名：advertDuration
     * 类型：Int
     * </pre>
     */
    protected int advertDuration;

    /**
     * <pre>
     * 字段名：广告区域宽度
     * 变量名：advertWidth
     * 类型：Int
     * </pre>
     */
    protected int advertWidth;

    /**
     * <pre>
     * 字段名：广告区域高度
     * 变量名：advertHeight
     * 类型：Int
     * </pre>
     */
    protected int advertHeight;

    /**
     * <pre>
     * 字段名：图片广告
     * 变量名：advertImage
     * 类型：String
     * </pre>
     */
    protected String advertImage;

    /**
     * <pre>
     * 字段名：广告的跳转链接
     * 变量名：advertHref
     * 类型：String
     * </pre>
     */
    protected String advertHref;

    /**
     * <pre>
     * 字段名：视频广告ID
     * 变量名：advertFlvVid
     * 类型：String
     * </pre>
     */
    protected String advertFlvVid;

    /**
     * <pre>
     * 字段名：视频广告链接
     * 变量名：advertFlvVid
     * 类型：String
     * </pre>
     */
    protected String advertFlvUrl;

    /**
     * <pre>
     * 字段名：播放器控制栏颜色
     * 变量名：playerColor
     * 类型：String
     * </pre>
     */
    protected String playerColor;

    /**
     * <pre>
     * 字段名：自动播放
     * 变量名：autoPlay
     * 类型：boolean
     * </pre>
     */
    protected boolean autoPlay;

    /**
     * <pre>
     * 字段名：一开始的暖场视频
     * 变量名：warmUpFlv
     * 类型：String
     * </pre>
     */
    protected String warmUpFlv;

    /**
     * <pre>
     * 字段名：观看密码限制，需要输入观看密码才能播放流
     * 变量名：passwdRestrict
     * 类型：boolean
     * </pre>
     */
    protected boolean passwdRestrict;

    /**
     * <pre>
     * 字段名：观看密码加密后的密文
     * 变量名：passwdEncrypted
     * 类型：String
     * </pre>
     */
    protected String passwdEncrypted;

    /**
     * <pre>
     * 字段名：仅推音频流
     * 变量名：isOnlyAudio
     * 类型：String
     * </pre>
     */
    protected String isOnlyAudio;

    /**
     * <pre>
     * 字段名：低延迟
     * 变量名：isLowLatency
     * 类型：String
     * </pre>
     */
    protected String isLowLatency;

    /**
     * <pre>
     * 字段名：直播拉流（播放）m3u8地址
     * 变量名：m3u8Url
     * 类型：String
     * </pre>
     */
    protected String m3u8Url;

    /**
     * <pre>
     * 字段名：直播拉流（播放）m3u8地址1
     * 变量名：m3u8Url1
     * 类型：String
     * </pre>
     */
    protected String m3u8Url1;

    /**
     * <pre>
     * 字段名：直播拉流（播放）m3u8地址2
     * 变量名：m3u8Url2
     * 类型：String
     * </pre>
     */
    protected String m3u8Url2;

    /**
     * <pre>
     * 字段名：直播拉流（播放）m3u8地址3
     * 变量名：m3u8Url3
     * 类型：String
     * </pre>
     */
    protected String m3u8Url3;

    /**
     * <pre>
     * 字段名：主持人姓名
     * 变量名：publisher
     * 类型：String
     * </pre>
     */
    protected String publisher;

    /**
     * <pre>
     * 字段名：频道的图标
     * 变量名：channelLogoImage
     * 类型：String
     * </pre>
     */
    protected String channelLogoImage;

    /**
     * <pre>
     * 字段名：服务器返回的时间戳（毫秒）
     * 变量名：currentTimeMillis
     * 类型：String
     * </pre>
     */
    protected Long currentTimeMillis;

    /**
     * 直播场景：alone 活动直播, ppt 三分屏
     */
    protected String scene;

    /**
     * 频道密码
     */
    protected String channelPasswd;
}
