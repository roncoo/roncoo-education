package com.roncoo.education.user.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "API-AUTH-微信绑定")
public class AuthBindingReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "code")
    private String code;

}
