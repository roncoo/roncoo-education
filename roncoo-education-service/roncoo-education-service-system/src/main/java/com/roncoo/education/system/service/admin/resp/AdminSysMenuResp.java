package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 是否显示(1: 显示；0:不显示)
     */
    @ApiModelProperty(value = "是否显示(1: 显示；0:隐藏)")
    private Integer isShow;
    /**
     * 菜单类型(1目录 2菜单 3按钮)
     */
    @ApiModelProperty(value = "菜单类型(1目录 2菜单 3按钮)")
    private Integer menuType;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String path;
    /**
     * 前端权限
     */
    @ApiModelProperty(value = "前端权限")
    private String permission;
    /**
     * 下级菜单集合
     */
    private List<AdminSysMenuResp> childrenList;
}
