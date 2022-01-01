package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiSysUserBiz;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.SysUserPageRESQ;
import com.roncoo.education.system.service.pc.resq.SysUserViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/system/pc/sys/user")
public class PcApiSysUserController extends BaseController {

    @Autowired
    private PcApiSysUserBiz biz;

    /**
     * 后台管理员分页列表接口
     */
    @ApiOperation(value = "后台管理员分页列表接口", notes = "后台管理员分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<SysUserPageRESQ>> list(@RequestBody SysUserPageREQ sysUserPageREQ) {
        return biz.list(sysUserPageREQ);
    }

    /**
     * 后台管理员添加接口
     */
    @ApiOperation(value = "后台管理员添加接口", notes = "后台管理员添加接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody SysUserSaveREQ sysUserSaveREQ) {
        return biz.save(sysUserSaveREQ);
    }

    /**
     * 后台管理员删除接口
     */
    @ApiOperation(value = "后台管理员删除接口", notes = "后台管理员删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody SysUserDeleteREQ sysUserSaveREQ) {
        return biz.delete(sysUserSaveREQ);
    }

    /**
     * 后台管理员更新接口
     */
    @ApiOperation(value = "后台管理员更新接口", notes = "后台管理员更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody SysUserUpdateREQ sysUserUpdateREQ) {
        return biz.update(sysUserUpdateREQ);
    }

    /**
     * 后台管理员查看接口
     */
    @ApiOperation(value = "后台管理员查看接口", notes = "后台管理员查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<SysUserViewRESQ> view(@RequestBody SysUserViewREQ sysUserViewREQ) {
        return biz.view(sysUserViewREQ);
    }

    /**
     * 后台管理员密码接口
     */
    @ApiOperation(value = "后台管理员密码接口", notes = "后台管理员更新密码接口")
    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public Result<Integer> updatePassword(@RequestBody SysUserUpdatePasswordREQ sysUserUpdatePasswordREQ) {
        return biz.updatePassword(sysUserUpdatePasswordREQ);
    }

}
