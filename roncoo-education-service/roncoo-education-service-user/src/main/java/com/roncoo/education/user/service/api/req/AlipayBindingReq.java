package com.roncoo.education.user.service.api.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付宝登录-绑定
 *
 * @author devin
 */
@Data
@Accessors(chain = true)
public class AlipayBindingReq implements Serializable {

    private static final long serialVersionUID = -7382271947926542667L;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "手机验证码", required = true)
    private String code;

    @ApiModelProperty(value = "unionId")
    private String unionId;

    @ApiModelProperty(value = "openId")
    private String openId;

    @ApiModelProperty(value = "注册来源", required = true)
    private Integer registerSource;
}
