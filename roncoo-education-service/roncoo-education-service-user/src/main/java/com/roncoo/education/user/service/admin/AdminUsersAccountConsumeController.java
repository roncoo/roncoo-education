package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminUsersAccountConsumeBiz;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumeEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumePageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumeSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountConsumePageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountConsumeViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-用户账户消费记录
 *
 * @author wujing
 */
@Tag(name = "admin-用户账户消费记录")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/users/account/consume")
public class AdminUsersAccountConsumeController {

    @NotNull
    private final AdminUsersAccountConsumeBiz biz;

    @Operation(summary = "用户账户消费记录分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUsersAccountConsumePageResp>> page(@RequestBody AdminUsersAccountConsumePageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "用户账户消费记录添加")
    @SysLog(value = "用户账户消费记录添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUsersAccountConsumeSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "用户账户消费记录查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUsersAccountConsumeViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "用户账户消费记录修改")
    @SysLog(value = "用户账户消费记录修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUsersAccountConsumeEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "用户账户消费记录删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "用户账户消费记录删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
