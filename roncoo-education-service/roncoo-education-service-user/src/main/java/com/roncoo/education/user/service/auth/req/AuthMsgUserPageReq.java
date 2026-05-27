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
public class AuthMsgUserPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;

    @Schema(description = "状态(1有效, 0无效)")
    private Integer statusId;

    @Schema(description = "是否阅读(1是;0否)")
    private Integer isRead;

    @Schema(description = "是否置顶(1是;0否)")
    private Integer isTop;
}
