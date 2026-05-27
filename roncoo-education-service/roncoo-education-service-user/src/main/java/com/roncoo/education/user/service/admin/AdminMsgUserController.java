package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminMsgUserBiz;
import com.roncoo.education.user.service.admin.req.AdminMsgUserEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgUserPageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgUserSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgUserPageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgUserViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-站内信用户记录表
 *
 * @author wujing
 */
@Tag(name = "admin-站内信用户记录表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/msg/user")
public class AdminMsgUserController {

    @NotNull
    private final AdminMsgUserBiz biz;

    @Operation(summary = "站内信用户记录表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminMsgUserPageResp>> page(@RequestBody AdminMsgUserPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "站内信用户记录表添加")
    @SysLog(value = "站内信用户记录表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminMsgUserSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "站内信用户记录表查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminMsgUserViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "站内信用户记录表修改")
    @SysLog(value = "站内信用户记录表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminMsgUserEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "站内信用户记录表删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "站内信用户记录表删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
