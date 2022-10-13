package com.roncoo.education.common.polyv.live.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建单个频道
 *
 * @author LYQ
 */
@Data
public class ChannelCreateRequest implements Serializable {

    private static final long serialVersionUID = 8358132334891912486L;

    /**
     * 直播名称，最大长度100--必填
     */
    private String name;

    /**
     * 直播场景 (topclass-大班课 、 double-双师课（该场景需开通） 、 train-企业培训 、 seminar-研讨会 、 alone-活动营销)--必填
     */
    private String newScene;

    /**
     * 直播模板 (ppt-文档+视频 、 alone-纯视频(专业) 、 topclass-纯视频(极速) 、 portrait_ppt-文档+视频(竖屏) 、 portrait_alone-视频(竖屏) 、 seminar-研讨会)
     * 直播场景为topclass、train或alone时，该字段支持ppt、alone、portrait_alone、portrait_alone，字段必填
     * 直播场景为double时，该字段支持ppt、alone，字段必填
     * 直播场景为seminar时，该字段默认seminar，可不传
     */
    private String template;

    /**
     * 讲师登录密码，直播场景不是研讨会时有效，长度6-16位，不传则由系统随机生成
     */
    private String channelPasswd;

    /**
     * 研讨会主持人密码，仅直播场景是研讨会时有效，长度6-16位，不传则由系统随机生成。研讨会主持人密码和参会人密码不能相同。
     */
    private String seminarHostPassword;

    /**
     * 研讨会参会人密码，仅直播场景是研讨会时有效，长度6-16位，不传则由系统随机生成。研讨会主持人密码和参会人密码不能相同。
     */
    private String seminarAttendeePassword;

    /**
     * 直播延迟 Y无延时 N普通延迟
     */
    private String pureRtcEnabled;

    /**
     * 转播类型 normal不开启、transmit发起转播、receive接收转播（该功能需要开通）
     */
    private String type;

    /**
     * 线上双师 transmit大房间、receive小房间
     */
    private String doubleTeacherType;

    /**
     * 中英双语直播开关 Y开、N关
     */
    private String cnAndEnLiveEnabled;

    /**
     * 封面图片地址，非保利威域名下的图片需调用上传频道所有装修图片素材上传
     */
    private String splashImg;

    /**
     * 连麦人数限制，最多16人
     */
    private Integer linkMicLimit;

    /**
     * 分类ID，可通过“查询直播分类”接口获取
     */
    private Integer categoryId;

    /**
     * 开始时间，时间戳，如：1629734400000
     */
    private Long startTime;
}
