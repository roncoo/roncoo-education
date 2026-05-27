package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminUsersBiz;
import com.roncoo.education.user.service.admin.req.AdminUsersEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-用户信息
 *
 * @author wujing
 */
@Tag(name = "admin-用户信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/users")
public class AdminUsersController {

    @NotNull
    private final AdminUsersBiz biz;

    @Operation(summary = "用户信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUsersPageResp>> page(@RequestBody AdminUsersPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "用户信息添加")
    @SysLog(value = "用户信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUsersSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "用户信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUsersViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "用户信息修改")
    @SysLog(value = "用户信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUsersEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "用户信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "用户信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
