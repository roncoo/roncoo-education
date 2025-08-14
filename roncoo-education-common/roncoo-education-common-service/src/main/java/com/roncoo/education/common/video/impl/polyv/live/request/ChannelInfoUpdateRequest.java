package com.roncoo.education.common.video.impl.polyv.live.request;


import lombok.Data;

import java.util.List;

/**
 * 修改频道信息
 *
 * @author LYQ
 */
@Data
public class ChannelInfoUpdateRequest {

    /**
     * 基础设置
     */
    private BasicSetting basicSetting;

    /**
     * 观看条件设置
     */
    private List<AuthSetting> authSettings;

    /**
     * 基础设置
     */
    @Data
    public static class BasicSetting {

        /**
         * 频道名称
         */
        private String name;

        /**
         * 频道密码，长度不能超过16位
         */
        private String channelPasswd;

        /**
         * 主持人名称
         */
        private String publisher;

        /**
         * 直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示
         */
        private Long startTime;

        /**
         * 累积观看数
         */
        private Integer pageView;

        /**
         * 点赞数
         */
        private Integer likes;

        /**
         * 封面图片地址
         */
        private String coverImg;

        /**
         * 引导图地址
         */
        private String splashImg;

        /**
         * 引导页开关
         * Y：开启
         * N：关闭
         */
        private String splashEnabled;

        /**
         * 直播介绍
         */
        private String desc;

        /**
         * 咨询提问开关
         * Y：开启
         * N：关闭
         */
        private String consultingMenuEnabled;

        /**
         * 是否限制最大观看人数
         * Y：是
         * N：否
         */
        private String maxViewerRestrict;

        /**
         * 最大在线人数
         */
        private Integer maxViewer;

        /**
         * 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“查询直播分类”接口得到）
         */
        private String categoryId;

        /**
         * 连麦人数，最大16人（范围大于等于-1，小于等于全局设置的连麦人数），-1：使用全局设置的连麦人数
         */
        private Integer linkMicLimit;

        /**
         * 是否增加转播关联（注：需要开启频道转播功能该参数才生效）
         * Y：表示增加关联
         * N：表示取消关联
         */
        private String operation;

        /**
         * 接收转播频道号，多个频道号用半角逗号"，"隔开（注：需要开启频道转播功能该参数才生效）
         */
        private String receiveChannelIds;

        /**
         * 是否关闭弹幕功能的开关
         * N：表示不关闭
         * Y：表示关闭
         */
        private String closeDanmu;

        /**
         * 默认是否显示弹幕信息开关
         * Y：表示显示
         * N：表示不显示
         */
        private String showDanmuInfoEnabled;

        /**
         * 是否开启无延时直播开关，为空时不对字段进行修改，直播中不能对该字段进行修改
         * Y：表示是
         * N：表示否
         */
        private String pureRtcEnabled;
    }

    /**
     * 观看条件设置
     */
    @Data
    public static class AuthSetting {

        /**
         * 主要观看条件为1，次要观看条件为2
         */
        private Integer rank;

        /**
         * 是否开启条件观看 N：关闭 Y：开启
         */
        private String enabled;

        /**
         * pay：付费观看 code：验证码观看 phone：白名单观看 info：登记观看 custom：自定义授权观看 external：外部授权观看 direct：直接授权观看
         */
        private String authType;

        /**
         * 当authType为pay时，设置参数，必填。欢迎语标题
         */
        private String payAuthTips;

        /**
         * 当authType为pay时，设置参数，必填。价格，单位为元
         */
        private String price;

        /**
         * 当authType为pay时，设置参数，非必填。付费有效截止日期，格式：yyyy-MM-dd HH:mm
         */
        private String watchEndTime;

        /**
         * 当authType为pay时，设置参数，非必填。付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效
         */
        private String validTimePeriod;

        /**
         * 当authType为code时，设置参数，必填。验证码
         */
        private String authCode;

        /**
         * 当authType为code时，设置参数，非必填。提示文案
         */
        private String qcodeTips;

        /**
         * 当authType为code时，设置参数，非必填。公众号二维码地址
         */
        private String qcodeImg;

        /**
         * 当authType为phone时，设置参数，非必填。提示文案
         */
        private String authTips;

        /**
         * 当authType为info时，设置参数，必填。登记观看信息，上限为5个
         */
        private List<InfoField> infoFields;

        /**
         * 当authType为external时，设置参数，必填。SecretKey
         */
        private String externalKey;

        /**
         * 当authType为external时，设置参数，必填。自定义url
         */
        private String externalUri;

        /**
         * 当authType为external时，设置参数，非必填。跳转地址
         */
        private String externalRedirectUri;

        /**
         * 当authType为custom时，设置参数，必填。SecretKey
         */
        private String customKey;

        /**
         * 当authType为custom时，设置参数，必填。自定义url
         */
        private String customUri;

        /**
         * 当authType为direct时，设置参数，必填。直接授权SecretKey
         */
        private String directKey;
    }

    /**
     * 登记观看信息
     */
    @Data
    public static class InfoField {

        /**
         * 当authType为info时，设置参数，非必填。登记信息名，最多为8字符
         */
        private String name;

        /**
         * 当authType为info时，设置参数，必填。登记类型
         * name：姓名
         * text：文本
         * mobile：手机号码
         * number：数字
         * option：下拉选项
         */
        private String type;

        /**
         * 当authType为info时，设置参数，非必填。下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符
         */
        private String options;

        /**
         * 当authType为info时，设置参数，非必填。文本框输入提示，最多为8字符
         */
        private String placeholder;

        /**
         * 当authType为info时，设置参数，非必填。短信验证开关，Y：开启，N：关闭
         */
        private String sms;
    }
}
