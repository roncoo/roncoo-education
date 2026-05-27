/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.api.req;

import com.roncoo.education.common.core.enums.LoginClientEnum;
import com.roncoo.education.common.core.enums.RegisterSourceEnum;
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
public class RegisterReq implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    @Schema(description = "手机", required = true)
    private String mobile;
    /**
     * 手机验证码
     */
    @Schema(description = "手机验证码", required = true)
    private String code;
    /**
     * 登录密码
     */
    @Schema(description = "登录密码，RSA加密", required = true)
    private String mobilePwdEncrypt;

    @Schema(description = "注册来源", required = false)
    private Integer registerSource = RegisterSourceEnum.SYS_PC.getCode();

    @Schema(description = "登录客户端", required = false)
    private Integer loginClient = LoginClientEnum.PC.getCode();

    @Schema(description = "登录IP", required = false)
    private String loginIp;

    @Schema(description = "浏览器", required = false)
    private String browser;

    @Schema(description = "操作系统", required = false)
    private String os;
}
