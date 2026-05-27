package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色信息-修改
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysRoleUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键", required = true)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @Schema(description = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @Schema(description = "排序", required = false)
    private Integer sort;
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
