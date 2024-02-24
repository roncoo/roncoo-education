package com.roncoo.education.system.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户登录")
public class AdminSysUserLoginResp implements Serializable {

    private static final long serialVersionUID = -5227677558610916215L;

    @ApiModelProperty(value = "登录账号", required = true)
    private String mobile;

    @ApiModelProperty(value = "昵称", required = true)
    private String realName;

    @ApiModelProperty(value = "token", required = true)
    private String token;

    @ApiModelProperty(value = "用户路由", required = true)
    private List<AdminSysUserLoginRouterResp> routerList;

    @ApiModelProperty(value = "用户菜单", required = true)
    private List<AdminSysMenuUserResp> menuList;

    @ApiModelProperty(value = "前端权限", required = true)
    private List<String> permissionList;
}
