package com.roncoo.education.user.service.api.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class WxLoginReq implements Serializable {

    private static final long serialVersionUID = -2877781106821535513L;

    @Schema(description = "授权类型，参考：LoginAuthTypeEnum", required = true)
    private Integer loginAuthType;

    @Schema(description = "重定向地址", required = true)
    private String redirectUrl;
}
