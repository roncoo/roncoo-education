package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 直播查询频道观看条件响应信息
 * </p>
 *
 * @author zhandehuang
 * @version 1.0
 * @date 2022/3/26 0026
 */
@Data
public class ChannelWatchConditionResponse implements Serializable {

    private static final long serialVersionUID = -5774970094722691758L;
    /**
     * 频道ID
     */
    private String channelId;

    /**
     * 用户ID
     */
    private String userId;


    /**
     * 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件）
     */
    private Integer rank;

    /**
     * 是否开启全局设置（Y/N）
     */
    private String globalSettingEnabled;

    /**
     * 是否开启观看条件(Y/N)
     */
    private String enabled;

    /**
     * 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external)
     */
    private String authType;

    /**
     * 验证码观看条件提示信息
     */
    private String codeAuthTips;

    /**
     * 验证码观看的验证码
     */
    private String authCode;

    /**
     * 验证码观看的二维码提示
     */
    private String qcodeTips;

    /**
     * 验证码观看的二维码图片
     */
    private String qcodeImg;

    /**
     * 付费观看提示信息
     */
    private String payAuthTips;

    /**
     * 付费观看的价格，单位：元
     */
    private BigDecimal price;

    /**
     * 付费观看，截止时间，为null表示：一次付费，永久有效
     */
    private Long watchEndTime;

    /**
     * 付费观看的截止时长（天）
     */
    private Integer validTimePeriod;

    /**
     * 自定义授权观看的key
     */
    private String customKey;

    /**
     * 自定义授权观看的接口地址
     */
    private String customUri;

    /**
     * 外部授权观看的key
     */
    private String externalKey;

    /**
     * 外部授权观看的接口地址
     */
    private String externalUri;

    /**
     * 外部授权观看，用户直接访问观看页时的跳转地址
     */
    private String externalRedirectUri;

    /**
     * 独立授权key
     */
    private String directKey;

    /**
     * 付费观看的试看开关，默认为N(Y：开、N：关)
     */
    private String trialWatchEnabled;

    /**
     * 试看时间，单位为分钟
     */
    private Integer trialWatchTime;

    /**
     * 试看截止日期，为null 表示对该频道永久有效
     */
    private Long trialWatchEndTime;

    /**
     * 白名单输入提示
     */
    private String whiteListInputTips;

    /**
     * 白名单入口文案
     */
    private String whiteListEntryText;

    /**
     * 白名单观看提示信息
     */
    private String authTips;

    /**
     * 登记观看描述字段
     */
    private String infoDesc;

    /**
     * 登记观看条件提示信息
     */
    private String infoAuthTips;
}
