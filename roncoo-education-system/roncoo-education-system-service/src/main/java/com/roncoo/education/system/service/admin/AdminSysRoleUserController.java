package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysRoleUserBiz;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserListReq;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminSysRoleUserResp;
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
@RestController
@RequestMapping(value = "/system/admin/sys/role/user")
public class AdminSysRoleUserController {

    @Autowired
    private AdminSysRoleUserBiz biz;

    /**
     * 列出角色用户关联信息接口
     */
    @ApiOperation(value = "列出角色用户关联信息接口", notes = "列出角色用户关联信息接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<AdminSysRoleUserResp>> list(@RequestBody AdminSysRoleUserListReq sysRoleUserListREQ) {
        return biz.list(sysRoleUserListREQ);
    }

    /**
     * 添加用户角色信息接口
     */
    @ApiOperation(value = "添加用户角色信息接口", notes = "添加用户角色信息接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody AdminSysRoleUserSaveReq sysRoleUserSaveREQ) {
        return biz.save(sysRoleUserSaveREQ);
    }

}
