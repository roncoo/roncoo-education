package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminUsersAccountBiz;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import jakarta.validation.constraints.NotNull;

/**
 * ADMIN-用户账户
 *
 * @author wujing
 */
@Api(tags = "admin-用户账户")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/users/account")
public class AdminUsersAccountController {

    @NotNull
    private final AdminUsersAccountBiz biz;

    @ApiOperation(value = "用户账户分页", notes = "用户账户分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUsersAccountPageResp>> page(@RequestBody AdminUsersAccountPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "用户账户添加", notes = "用户账户添加")
    @SysLog(value = "用户账户添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUsersAccountSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "用户账户查看", notes = "用户账户查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUsersAccountViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "用户账户修改", notes = "用户账户修改")
    @SysLog(value = "用户账户修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUsersAccountEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "用户账户删除", notes = "用户账户删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLog(value = "用户账户删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
