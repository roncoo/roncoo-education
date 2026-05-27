package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysMenuResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 是否显示(1: 显示；0:不显示)
     */
    @Schema(description = "是否显示(1: 显示；0:隐藏)")
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
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String menuIcon;
    /**
     * 菜单路径
     */
    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "后端接口")
    private String apis;

    /**
     * 前端权限
     */
    @Schema(description = "前端权限")
    private String permission;
    /**
     * 下级菜单集合
     */
    private List<AdminSysMenuResp> childrenList;
}
