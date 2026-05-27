package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色信息-删除
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysRoleDeleteReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键", required = true)
    private Long id;

}
