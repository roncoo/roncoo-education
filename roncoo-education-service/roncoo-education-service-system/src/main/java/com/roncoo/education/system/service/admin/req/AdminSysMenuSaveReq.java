package com.roncoo.education.system.service.admin.req;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "父ID")
    private Long parentId;

    @ApiModelProperty(value = "是否显示")
    private String isShow;
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

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "后端接口")
    private String apis;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
