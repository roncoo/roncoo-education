package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminMsgBiz;
import com.roncoo.education.user.service.admin.req.AdminMsgEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgPageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgPageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-站内信息表
 *
 * @author wujing
 */
@Tag(name = "admin-站内信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/msg")
public class AdminMsgController {

    @NotNull
    private final AdminMsgBiz biz;

    @Operation(summary = "站内信息表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminMsgPageResp>> page(@RequestBody AdminMsgPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "站内信息表添加")
    @SysLog(value = "站内信息表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminMsgSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "站内信息表查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminMsgViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "站内信息表修改")
    @SysLog(value = "站内信息表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminMsgEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "站内信息表删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "站内信息表删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
