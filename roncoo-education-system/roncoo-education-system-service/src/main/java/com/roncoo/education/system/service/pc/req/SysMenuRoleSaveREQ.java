package com.roncoo.education.system.service.pc.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单角色关联表-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysMenuRoleSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID集合
     */
    private List<Long> menuId;
    /**
     * 角色ID
     */
    private Long roleId;
}
