package com.roncoo.education.user.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class SendSmsLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 短信模板
     */
    private String template;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 验证码
     */
    private String smsCode;
    /**
     * 是否发送成功(1发送成功，0:发送失败)
     */
    private Integer isSuccess;

}
