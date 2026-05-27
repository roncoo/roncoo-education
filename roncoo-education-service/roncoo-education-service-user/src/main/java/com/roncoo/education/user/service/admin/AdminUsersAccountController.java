package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminUsersAccountBiz;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-用户账户
 *
 * @author wujing
 */
@Tag(name = "admin-用户账户")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/users/account")
public class AdminUsersAccountController {

    @NotNull
    private final AdminUsersAccountBiz biz;

    @Operation(summary = "用户账户分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUsersAccountPageResp>> page(@RequestBody AdminUsersAccountPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "用户账户添加")
    @SysLog(value = "用户账户添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUsersAccountSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "用户账户查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUsersAccountViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "用户账户修改")
    @SysLog(value = "用户账户修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUsersAccountEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "用户账户删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "用户账户删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
