package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单角色关联表-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysMenuRoleListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID", required = true)
    private Long roleId;
}
