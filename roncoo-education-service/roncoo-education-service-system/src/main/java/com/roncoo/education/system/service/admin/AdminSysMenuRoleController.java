package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysMenuRoleBiz;
import com.roncoo.education.system.service.admin.req.AdminSysMenuRoleListReq;
import com.roncoo.education.system.service.admin.req.AdminSysMenuRoleSaveReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@Api(tags = "admin-菜单角色接口")
@RestController
@RequestMapping(value = "/system/admin/sys/menu/role")
public class AdminSysMenuRoleController {

    @Autowired
    private AdminSysMenuRoleBiz biz;

    /**
     * 列出菜单角色关联信息接口
     */
    @ApiOperation(value = "菜单ID列出", notes = "根据角色ID列出该角色的所有菜单ID")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<Long>> list(@RequestBody AdminSysMenuRoleListReq req) {
        return biz.list(req);
    }

    /**
     * 添加角色菜单关联信息接口
     */
    @ApiOperation(value = "角色菜单保存", notes = "角色菜单添加")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody AdminSysMenuRoleSaveReq sysMenuRoleSaveREQ) {
        return biz.save(sysMenuRoleSaveREQ);
    }

}
