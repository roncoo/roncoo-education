package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteLinkBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteLinkPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteLinkViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-站点友情链接
 *
 * @author wujing
 */
@Tag(name = "admin-站点友情链接")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/link")
public class AdminWebsiteLinkController {

    @NotNull
    private final AdminWebsiteLinkBiz biz;

    @Operation(summary = "站点友情链接分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteLinkPageResp>> page(@RequestBody AdminWebsiteLinkPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "站点友情链接添加")
    @SysLog(value = "站点友情链接添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteLinkSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "站点友情链接查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminWebsiteLinkViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "站点友情链接修改")
    @SysLog(value = "站点友情链接修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteLinkEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "站点友情链接删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "站点友情链接删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "WebsiteLink"));
    }
}
