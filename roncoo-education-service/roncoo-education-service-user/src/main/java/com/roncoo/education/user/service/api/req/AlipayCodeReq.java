package com.roncoo.education.user.service.api.req;

import com.roncoo.education.common.core.enums.ClientTypeEnum;
import com.roncoo.education.common.core.enums.LoginAuthTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付宝登录-授权码
 *
 * @author devin
 */
@Data
@Accessors(chain = true)
public class AlipayCodeReq implements Serializable {

    private static final long serialVersionUID = -7382271947926542667L;

    /**
     * @see LoginAuthTypeEnum
     */
    @ApiModelProperty(value = "授权类型，参考：LoginAuthTypeEnum", required = true)
    private Integer loginAuthType;

    /**
     * @see ClientTypeEnum
     */
    @ApiModelProperty(value = "客户端类型，参考：ClientTypeEnum", required = true)
    private Integer clientType;

    @ApiModelProperty(value = "授权码", required = true)
    private String code;
}
