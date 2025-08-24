package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.common.base.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.system.service.admin.biz.AdminSysUserBiz;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysUserPageResp;
import com.roncoo.education.system.service.admin.resp.AdminSysUserViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Api(tags = "admin-用户接口")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/system/admin/sys/user")
public class AdminSysUserController {

    @NotNull
    private final AdminSysUserBiz biz;

    /**
     * 后台管理员分页列表接口
     */
    @ApiOperation(value = "后台管理员分页列表接口", notes = "后台管理员分页列表接口")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<Page<AdminSysUserPageResp>> listForPage(@RequestBody AdminSysUserPageReq sysUserPageREQ) {
        return biz.listForPage(sysUserPageREQ);
    }

    /**
     * 后台管理员添加接口
     */
    @ApiOperation(value = "后台管理员添加接口", notes = "后台管理员添加接口")
    @SysLog(value = "后台管理员添加接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody AdminSysUserSaveReq sysUserSaveREQ) {
        return biz.save(sysUserSaveREQ);
    }

    /**
     * 后台管理员删除接口
     */
    @ApiOperation(value = "后台管理员删除接口", notes = "后台管理员删除接口")
    @SysLog(value = "后台管理员删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public Result<String> delete(@RequestBody AdminSysUserDeleteReq sysUserSaveREQ) {
        return biz.delete(sysUserSaveREQ);
    }

    /**
     * 后台管理员修改接口
     */
    @ApiOperation(value = "后台管理员修改接口", notes = "后台管理员修改接口")
    @SysLog(value = "后台管理员修改接口")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result<String> update(@RequestBody AdminSysUserUpdateReq sysUserUpdateREQ) {
        return biz.update(sysUserUpdateREQ);
    }

    /**
     * 后台管理员查看接口
     */
    @ApiOperation(value = "后台管理员查看接口", notes = "后台管理员查看接口")
    @SysLogCache
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AdminSysUserViewResp> view(@RequestBody AdminSysUserViewReq sysUserViewREQ) {
        return biz.view(sysUserViewREQ);
    }

    /**
     * 后台管理员密码接口
     */
    @ApiOperation(value = "后台管理员密码修改", notes = "后台管理员密码修改")
    @SysLog(value = "后台管理员密码修改")
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public Result<String> updatePassword(@RequestBody AdminSysUserUpdatePasswordReq sysUserUpdatePasswordREQ) {
        return biz.updatePassword(sysUserUpdatePasswordREQ);
    }

    @ApiOperation(value = "获取当前登录用户", notes = "获取当前登录用户")
    @GetMapping(value = "/current")
    public Result<AdminSysUserViewResp> currentView() {
        AdminSysUserViewReq req = new AdminSysUserViewReq();
        req.setId(ThreadContext.userId());
        return biz.view(req);
    }

    @ApiOperation(value = "排序", notes = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "SysUser"));
    }

}
