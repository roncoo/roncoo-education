package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysRoleUserBiz;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserListReq;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserSaveReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@Api(tags = "admin-角色用户接口")
@RestController
@RequestMapping(value = "/system/admin/sys/role/user")
public class AdminSysRoleUserController {

    @Autowired
    private AdminSysRoleUserBiz biz;

    /**
     * 列出角色用户关联信息接口
     */
    @ApiOperation(value = "角色用户列出", notes = "根据用户ID列出该用户的所有角色")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<Long>> list(@RequestBody AdminSysRoleUserListReq sysRoleUserListREQ) {
        return biz.list(sysRoleUserListREQ);
    }

    /**
     * 添加用户角色信息接口
     */
    @ApiOperation(value = "角色用户保存", notes = "用户角色添加接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody AdminSysRoleUserSaveReq sysRoleUserSaveREQ) {
        return biz.save(sysRoleUserSaveREQ);
    }

}
