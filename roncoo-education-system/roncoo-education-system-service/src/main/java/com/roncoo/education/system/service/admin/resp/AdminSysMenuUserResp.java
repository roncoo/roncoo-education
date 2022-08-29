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
public class AdminSysMenuUserResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;
    /**
     * 菜单英文
     */
    @ApiModelProperty(value = "菜单英文")
    private String nameEn;
    /**
     * 菜单类型(1目录 2菜单 3权限)
     */
    @ApiModelProperty(value = "菜单类型(1目录 2菜单 3权限)")
    private Integer menuType;
    /**
     * 接口URL
     */
    @ApiModelProperty(value = "接口URL")
    private String path;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 目标
     */
    @ApiModelProperty(value = "icon")
    private String targetName;

    private List<AdminSysMenuUserResp> children;
}
