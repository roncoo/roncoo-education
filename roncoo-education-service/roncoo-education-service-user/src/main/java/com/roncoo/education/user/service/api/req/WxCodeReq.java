package com.roncoo.education.user.service.api.req;

import com.roncoo.education.common.core.enums.ClientTypeEnum;
import com.roncoo.education.common.core.enums.LoginAuthTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信授权
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class WxCodeReq implements Serializable {

    private static final long serialVersionUID = -2877781106821535513L;

    @ApiModelProperty(value = "授权回调code值")
    private String code;

    /**
     * @see LoginAuthTypeEnum
     */
    @ApiModelProperty(value = "授权类型，参考：LoginAuthTypeEnum", required = true)
    private Integer loginAuthType;

    /**
     * @see ClientTypeEnum
     */
    @ApiModelProperty(value = "客户端类型，参考：ClientTypeEnum")
    private Integer clientType;
}
