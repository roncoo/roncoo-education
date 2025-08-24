package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.annotation.SysLog;
import com.roncoo.education.common.annotation.SysLogCache;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminUsersAccountConsumeBiz;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumeEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumePageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumeSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountConsumePageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountConsumeViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import jakarta.validation.constraints.NotNull;

/**
 * ADMIN-用户账户消费记录
 *
 * @author wujing
 */
@Api(tags = "admin-用户账户消费记录")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/users/account/consume")
public class AdminUsersAccountConsumeController {

    @NotNull
    private final AdminUsersAccountConsumeBiz biz;

    @ApiOperation(value = "用户账户消费记录分页", notes = "用户账户消费记录分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUsersAccountConsumePageResp>> page(@RequestBody AdminUsersAccountConsumePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "用户账户消费记录添加", notes = "用户账户消费记录添加")
    @SysLog(value = "用户账户消费记录添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUsersAccountConsumeSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "用户账户消费记录查看", notes = "用户账户消费记录查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUsersAccountConsumeViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "用户账户消费记录修改", notes = "用户账户消费记录修改")
    @SysLog(value = "用户账户消费记录修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUsersAccountConsumeEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "用户账户消费记录删除", notes = "用户账户消费记录删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLog(value = "用户账户消费记录删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
