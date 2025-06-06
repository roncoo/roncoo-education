/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.api.req;

import com.roncoo.education.common.core.enums.LoginClientEnum;
import io.swagger.annotations.ApiModelProperty;
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
public class LoginReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "登录密码，RSA加密", required = true)
    private String mobilePwdEncrypt;

    @ApiModelProperty(value = "验证KEY", required = true)
    private String verToken;

    @ApiModelProperty(value = "验证码", required = true)
    private String verCode;

    @ApiModelProperty(value = "登录客户端", required = false)
    private Integer loginClient = LoginClientEnum.PC.getCode();

    @ApiModelProperty(value = "登录IP", required = false)
    private String loginIp;

    @ApiModelProperty(value = "浏览器", required = false)
    private String browser;

    @ApiModelProperty(value = "操作系统", required = false)
    private String os;
}
