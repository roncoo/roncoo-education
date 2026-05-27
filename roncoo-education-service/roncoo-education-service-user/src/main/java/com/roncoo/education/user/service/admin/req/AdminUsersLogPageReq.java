package com.roncoo.education.user.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-用户登录日志
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-用户登录日志分页")
public class AdminUsersLogPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "登录客户端")
    private Integer loginClient;

    @Schema(description = "登录方式")
    private Integer loginType;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
