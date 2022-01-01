package com.roncoo.education.system.service.pc.resq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单角色关联表-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysMenuRoleListRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单角色关联表集合
     */
    @ApiModelProperty(value = "菜单角色关联菜单ID集合")
    private List<String> list = new ArrayList<>();
}
