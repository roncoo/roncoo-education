package com.roncoo.education.user.service.api.req;

import com.roncoo.education.common.core.enums.ClientTypeEnum;
import com.roncoo.education.common.core.enums.LoginAuthTypeEnum;
import com.roncoo.education.common.core.enums.LoginTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "授权回调code值")
    private String code;

    /**
     * @see LoginAuthTypeEnum
     */
    @Schema(description = "授权类型，参考：LoginAuthTypeEnum", required = true)
    private Integer loginAuthType;

    /**
     * @see ClientTypeEnum
     */
    @Schema(description = "客户端类型，参考：ClientTypeEnum")
    private Integer clientType;

    @Schema(description = "登录方式", required = false)
    private Integer loginType = LoginTypeEnum.WX_WEB_AUTH.getCode();

    @Schema(description = "浏览器", required = false)
    private String browser;

    @Schema(description = "操作系统", required = false)
    private String os;
}
