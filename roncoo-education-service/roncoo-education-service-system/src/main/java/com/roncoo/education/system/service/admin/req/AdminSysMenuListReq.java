package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单信息-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysMenuListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单名称", required = false)
    private String menuName;

    @Schema(description = "状态", required = false)
    private Integer statusId;

}
