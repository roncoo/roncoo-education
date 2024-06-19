package com.roncoo.education.user.service.api.req;

import com.roncoo.education.common.core.enums.LoginTypeEnum;
import com.roncoo.education.common.core.enums.RegisterSourceEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信登录
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class WxBindingReq implements Serializable {

    private static final long serialVersionUID = -2877781106821535513L;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机", required = true)
    private String mobile;
    /**
     * 手机验证码
     */
    @ApiModelProperty(value = "手机验证码", required = true)
    private String code;
    /**
     * UnionId
     */
    @ApiModelProperty(value = "UnionId")
    private String unionId;
    /**
     * OpenId
     */
    @ApiModelProperty(value = "OpenId")
    private String openId;

    @ApiModelProperty(value = "注册来源", required = false)
    private Integer registerSource = RegisterSourceEnum.SYS_PC_WX.getCode();

    @ApiModelProperty(value = "登录方式", required = false)
    private Integer loginType = LoginTypeEnum.WX_WEB_AUTH.getCode();

    @ApiModelProperty(value = "浏览器", required = false)
    private String browser;

    @ApiModelProperty(value = "操作系统", required = false)
    private String os;
}
