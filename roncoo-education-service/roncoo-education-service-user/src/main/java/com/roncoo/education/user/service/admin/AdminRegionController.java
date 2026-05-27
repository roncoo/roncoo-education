package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminRegionBiz;
import com.roncoo.education.user.service.admin.req.AdminRegionEditReq;
import com.roncoo.education.user.service.admin.req.AdminRegionPageReq;
import com.roncoo.education.user.service.admin.req.AdminRegionSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminRegionPageResp;
import com.roncoo.education.user.service.admin.resp.AdminRegionViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-行政区域表
 *
 * @author wujing
 */
@Tag(name = "admin-行政区域表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/region")
public class AdminRegionController {

    @NotNull
    private final AdminRegionBiz biz;

    @Operation(summary = "行政区域表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminRegionPageResp>> page(@RequestBody AdminRegionPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "行政区域表添加")
    @SysLog(value = "行政区域表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminRegionSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "行政区域表查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminRegionViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "行政区域表修改")
    @SysLog(value = "行政区域表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminRegionEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "行政区域表删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "行政区域表删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
