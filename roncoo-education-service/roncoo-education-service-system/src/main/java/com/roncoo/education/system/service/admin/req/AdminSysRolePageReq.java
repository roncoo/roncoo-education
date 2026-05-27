package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色信息-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysRolePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态(1:正常，0:禁用)
     */
    @Schema(description = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 名称
     */
    @Schema(description = "名称", required = false)
    private String roleName;

    /**
     * 当前页
     */
    @Schema(description = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @Schema(description = "每页记录数", required = true)
    private int pageSize = 20;
}
