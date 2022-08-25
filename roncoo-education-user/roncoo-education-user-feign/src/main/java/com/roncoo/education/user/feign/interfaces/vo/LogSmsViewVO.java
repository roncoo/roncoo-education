package com.roncoo.education.user.feign.interfaces.vo;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户短信发送日志
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class LogSmsViewVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

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
