package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "用户路由")
public class AdminSysUserLoginRouterResp implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
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
    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;
    /**
     * 前端权限
     */
    @Schema(description = "前端权限")
    private String permission;
}
