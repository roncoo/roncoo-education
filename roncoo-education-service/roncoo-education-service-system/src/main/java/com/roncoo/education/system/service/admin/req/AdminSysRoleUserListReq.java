package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色用户关联表-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysRoleUserListReq implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID", required = true)
    private Long userId;
}
