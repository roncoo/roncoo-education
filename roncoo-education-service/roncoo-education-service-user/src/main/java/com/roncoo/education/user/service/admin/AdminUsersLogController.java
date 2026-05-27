package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminUsersLogBiz;
import com.roncoo.education.user.service.admin.req.AdminUsersLogEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersLogPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersLogSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersLogPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersLogViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-用户登录日志
 *
 * @author wujing
 */
@Tag(name = "admin-用户登录日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/users/log")
public class AdminUsersLogController {

    @NotNull
    private final AdminUsersLogBiz biz;

    @Operation(summary = "用户登录日志分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUsersLogPageResp>> page(@RequestBody AdminUsersLogPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "用户登录日志添加")
    @SysLog(value = "用户登录日志添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUsersLogSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "用户登录日志查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUsersLogViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "用户登录日志修改")
    @SysLog(value = "用户登录日志修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUsersLogEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "用户登录日志删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "用户登录日志删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
