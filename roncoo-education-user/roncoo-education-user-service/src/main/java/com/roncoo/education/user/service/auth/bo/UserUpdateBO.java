package com.roncoo.education.user.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户基本信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class UserUpdateBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    /**
     * 新登录密码
     */
    @ApiModelProperty(value = "新登录密码")
    private String newPassword;
    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;
    /**
     * clientId
     */
    @ApiModelProperty(value = "clientId")
    private String clientId;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;
}
