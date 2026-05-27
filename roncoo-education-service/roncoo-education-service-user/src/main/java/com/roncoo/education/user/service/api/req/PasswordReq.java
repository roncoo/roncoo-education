/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.api.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class PasswordReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号", required = true)
    private String mobile;
    /**
     * 手机验证码
     */
    @Schema(description = "手机验证码", required = true)
    private String verificationCode;
    /**
     * 登录密码
     */
    @Schema(description = "密码", required = true)
    private String mobilePwdEncrypt;
}
