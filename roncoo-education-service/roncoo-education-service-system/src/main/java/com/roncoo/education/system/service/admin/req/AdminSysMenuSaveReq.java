package com.roncoo.education.system.service.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单信息-保存
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
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String menuUrl;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "权限认证值")
    private String authValue;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 菜单类型(1目录 2菜单 3权限)
     */
    @ApiModelProperty(value = "菜单类型(1目录 2菜单 3权限)")
    private Integer menuType;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
