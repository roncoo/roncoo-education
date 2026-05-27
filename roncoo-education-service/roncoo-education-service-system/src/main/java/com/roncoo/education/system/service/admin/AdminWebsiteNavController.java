package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteNavBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteNavPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteNavViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-头部导航
 *
 * @author wujing
 */
@Tag(name = "admin-头部导航")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/nav")
public class AdminWebsiteNavController {

    @NotNull
    private final AdminWebsiteNavBiz biz;

    @Operation(summary = "头部导航分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteNavPageResp>> page(@RequestBody AdminWebsiteNavPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "头部导航添加")
    @SysLog(value = "头部导航添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteNavSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "头部导航查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminWebsiteNavViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "头部导航修改")
    @SysLog(value = "头部导航修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteNavEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "头部导航删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "头部导航删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "WebsiteNav"));
    }
}
