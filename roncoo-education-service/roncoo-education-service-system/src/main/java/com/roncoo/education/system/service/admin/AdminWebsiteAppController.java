package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteAppBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAppEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAppPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAppSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAppPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAppViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-app版本管理
 *
 * @author fengyw
 */
@Tag(name = "admin-app版本管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/app")
public class AdminWebsiteAppController {

    @NotNull
    private final AdminWebsiteAppBiz biz;

    @Operation(summary = "app版本管理分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteAppPageResp>> page(@RequestBody AdminWebsiteAppPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "app版本管理添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteAppSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "app版本管理查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @GetMapping(value = "/view")
    public Result<AdminWebsiteAppViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "app版本管理修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteAppEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "app版本管理删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "WebsiteApp"));
    }
}
