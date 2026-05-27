package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单信息-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysMenuSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "是否显示")
    private String isShow;
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
    /**
     * 菜单图标
     */
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
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
}
