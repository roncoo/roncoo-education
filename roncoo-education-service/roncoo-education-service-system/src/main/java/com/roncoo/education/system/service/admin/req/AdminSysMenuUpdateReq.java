package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysMenuUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 父ID
     */
    @Schema(description = "父ID")
    private Long parentId;
    /**
     * 是否显示(1: 显示；0:不显示)
     */
    @Schema(description = "是否显示")
    private Boolean isShow;
    /**
     * 菜单类型(1目录 2菜单 3按钮)
     */
    @Schema(description = "菜单类型(1目录 2菜单 3按钮)")
    private Integer menuType;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "菜单图标")
    private String menuIcon;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "权限标识")
    private String permission;

    @Schema(description = "后端接口")
    private String apis;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
