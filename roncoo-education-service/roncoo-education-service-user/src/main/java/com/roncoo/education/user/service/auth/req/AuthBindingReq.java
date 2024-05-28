package com.roncoo.education.user.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 微信绑定
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-微信绑定")
public class AuthBindingReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "code")
    private String code;

}
