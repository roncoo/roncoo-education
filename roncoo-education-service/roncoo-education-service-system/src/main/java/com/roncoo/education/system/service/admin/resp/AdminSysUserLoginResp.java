package com.roncoo.education.system.service.admin.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "用户登录")
public class AdminSysUserLoginResp implements Serializable {

    private static final long serialVersionUID = -5227677558610916215L;

    @Schema(description = "登录账号", required = true)
    private String mobile;

    @Schema(description = "昵称", required = true)
    private String realName;

    @Schema(description = "token", required = true)
    private String token;

    @Schema(description = "用户路由", required = true)
    private List<AdminSysUserLoginRouterResp> routerList;

    @Schema(description = "用户菜单", required = true)
    private List<AdminSysMenuUserResp> menuList;

    @Schema(description = "前端权限", required = true)
    private List<String> permissionList;
}
