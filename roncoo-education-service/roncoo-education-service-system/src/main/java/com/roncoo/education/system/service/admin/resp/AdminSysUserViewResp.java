package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 后台用户信息-查看
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysUserViewResp implements Serializable {

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
     * 手机
     */
    @Schema(description = "手机")
    private String mobile;
    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    @Schema(description = "用户路由", required = true)
    private List<AdminSysUserLoginRouterResp> routerList;

    @Schema(description = "用户菜单", required = true)
    private List<AdminSysMenuUserResp> menuList;

    @Schema(description = "前端权限", required = true)
    private List<String> permissionList;
}
