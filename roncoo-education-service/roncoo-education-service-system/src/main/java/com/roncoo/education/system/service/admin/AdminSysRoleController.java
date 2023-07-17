package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysRoleBiz;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysRolePageResp;
import com.roncoo.education.system.service.admin.resp.AdminSysRoleViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色信息
 *
 * @author wujing
 */
@Api(tags = "admin-角色接口")
@RestController
@RequestMapping(value = "/system/admin/sys/role")
public class AdminSysRoleController {

    @Autowired
    private AdminSysRoleBiz biz;

    /**
     * 角色分页列表接口
     */
    @ApiOperation(value = "角色分页", notes = "角色分页列表接口")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<Page<AdminSysRolePageResp>> listForPage(@RequestBody AdminSysRolePageReq sysRolePageREQ) {
        return biz.listForPage(sysRolePageREQ);
    }

    /**
     * 角色添加接口
     */
    @ApiOperation(value = "角色保存", notes = "角色添加接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody AdminSysRoleSaveReq sysRoleSaveREQ) {
        return biz.save(sysRoleSaveREQ);
    }

    /**
     * 角色删除接口
     */
    @ApiOperation(value = "角色删除接口", notes = "角色删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<String> delete(@RequestBody AdminSysRoleDeleteReq sysRoleDeleteREQ) {
        return biz.delete(sysRoleDeleteREQ);
    }

    /**
     * 角色更新接口
     */
    @ApiOperation(value = "角色更新接口", notes = "角色更新接口")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result<String> update(@RequestBody AdminSysRoleUpdateReq sysRoleUpdateREQ) {
        return biz.update(sysRoleUpdateREQ);
    }

    /**
     * 角色查看接口
     */
    @ApiOperation(value = "角色查看接口", notes = "角色查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AdminSysRoleViewResp> view(@RequestBody AdminSysRoleViewReq sysRoleViewREQ) {
        return biz.view(sysRoleViewREQ);
    }

}
