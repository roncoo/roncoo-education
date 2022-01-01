package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiSysRoleUserBiz;
import com.roncoo.education.system.service.pc.req.SysRoleUserListREQ;
import com.roncoo.education.system.service.pc.req.SysRoleUserSaveREQ;
import com.roncoo.education.system.service.pc.resq.SysRoleUserListRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/system/pc/sys/role/user")
public class PcApiSysRoleUserController extends BaseController {

    @Autowired
    private PcApiSysRoleUserBiz biz;

    /**
     * 列出角色用户关联信息接口
     */
    @ApiOperation(value = "列出角色用户关联信息接口", notes = "列出角色用户关联信息接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<SysRoleUserListRESQ> list(@RequestBody SysRoleUserListREQ sysRoleUserListREQ) {
        return biz.list(sysRoleUserListREQ);
    }

    /**
     * 添加用户角色信息接口
     */
    @ApiOperation(value = "添加用户角色信息接口", notes = "添加用户角色信息接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody SysRoleUserSaveREQ sysRoleUserSaveREQ) {
        return biz.save(sysRoleUserSaveREQ);
    }

}
