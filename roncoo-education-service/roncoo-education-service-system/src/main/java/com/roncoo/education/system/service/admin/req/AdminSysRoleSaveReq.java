package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色信息-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysRoleSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", required = true)
    private String roleName;
    /**
     * 备注
     */
    @Schema(description = "备注", required = false)
    private String remark;
}
