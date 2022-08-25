package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysMenuRoleBiz;
import com.roncoo.education.system.service.admin.req.SysMenuRoleListREQ;
import com.roncoo.education.system.service.admin.req.SysMenuRoleSaveREQ;
import com.roncoo.education.system.service.admin.resp.SysMenuRoleListRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/system/admin/sys/menu/role")
public class AdminSysMenuRoleController {

    @Autowired
    private AdminSysMenuRoleBiz biz;

    /**
     * 列出菜单角色关联信息接口
     */
    @ApiOperation(value = "列出菜单角色关联信息接口", notes = "列出菜单角色关联信息接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<SysMenuRoleListRESQ> list(@RequestBody SysMenuRoleListREQ sysMenuRoleListREQ) {
        return biz.list(sysMenuRoleListREQ);
    }

    /**
     * 添加角色菜单关联信息接口
     */
    @ApiOperation(value = "添加角色菜单关联信息接口", notes = "添加角色菜单关联信息接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody SysMenuRoleSaveREQ sysMenuRoleSaveREQ) {
        return biz.save(sysMenuRoleSaveREQ);
    }

}
