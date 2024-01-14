package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户路由")
public class AdminSysUserLoginRouterResp implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String path;
    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    private String component;
    /**
     * 前端权限
     */
    @ApiModelProperty(value = "前端权限")
    private String permission;
}
