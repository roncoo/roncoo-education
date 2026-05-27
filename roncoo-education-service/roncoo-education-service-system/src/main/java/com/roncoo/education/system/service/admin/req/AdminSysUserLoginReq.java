package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;


/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "用户登录")
public class AdminSysUserLoginReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "登录账号不能为空")
    @Schema(description = "登录账号", required = true)
    private String mobile;

    @NotBlank(message = "登录密码不能为空")
    @Schema(description = "登录密码", required = true)
    private String mobilePwdEncrypt;

    @NotBlank(message = "验证KEY不能为空")
    @Schema(description = "验证KEY")
    private String verToken;

    @NotBlank(message = "验证码不能为空")
    @Schema(description = "验证码")
    private String verCode;
}
