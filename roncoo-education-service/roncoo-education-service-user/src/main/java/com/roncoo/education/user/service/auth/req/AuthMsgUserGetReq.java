package com.roncoo.education.user.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-站内信用户记录表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-站内信用户记录表")
public class AuthMsgUserGetReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;
}
