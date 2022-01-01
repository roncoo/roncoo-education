package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiSysRoleBiz;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.SysRolePageRESQ;
import com.roncoo.education.system.service.pc.resq.SysRoleViewRESQ;
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
@RestController
@RequestMapping(value = "/system/pc/sys/role")
public class PcApiSysRoleController extends BaseController {

    @Autowired
    private PcApiSysRoleBiz biz;

    /**
     * 角色分页列表接口
     */
    @ApiOperation(value = "角色分页列表接口", notes = "角色分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<SysRolePageRESQ>> list(@RequestBody SysRolePageREQ sysRolePageREQ) {
        return biz.list(sysRolePageREQ);
    }

    /**
     * 角色添加接口
     */
    @ApiOperation(value = "角色添加接口", notes = "角色添加接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody SysRoleSaveREQ sysRoleSaveREQ) {
        return biz.save(sysRoleSaveREQ);
    }

    /**
     * 角色删除接口
     */
    @ApiOperation(value = "角色删除接口", notes = "角色删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody SysRoleDeleteREQ sysRoleDeleteREQ) {
        return biz.delete(sysRoleDeleteREQ);
    }

    /**
     * 角色更新接口
     */
    @ApiOperation(value = "角色更新接口", notes = "角色更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody SysRoleUpdateREQ sysRoleUpdateREQ) {
        return biz.update(sysRoleUpdateREQ);
    }

    /**
     * 角色查看接口
     */
    @ApiOperation(value = "角色查看接口", notes = "角色查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<SysRoleViewRESQ> view(@RequestBody SysRoleViewREQ sysRoleViewREQ) {
        return biz.view(sysRoleViewREQ);
    }

}
