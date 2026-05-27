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
public class AdminSysMenuUserResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "菜单类型(1目录 2菜单 3按钮)")
    private Integer menuType;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "菜单名称")
    private String menuIcon;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "权限标识")
    private String permission;

    private List<AdminSysMenuUserResp> children;
}
