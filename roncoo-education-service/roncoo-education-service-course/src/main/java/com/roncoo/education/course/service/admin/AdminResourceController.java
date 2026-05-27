package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminResourceBiz;
import com.roncoo.education.course.service.admin.req.*;
import com.roncoo.education.course.service.admin.resp.AdminPreviewResp;
import com.roncoo.education.course.service.admin.resp.AdminResourcePageResp;
import com.roncoo.education.course.service.admin.resp.AdminResourceViewResp;
import com.roncoo.education.course.service.admin.resp.AdminVodConfigResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-课程视频信息
 *
 * @author wujing
 */
@Tag(name = "admin-课程视频信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/resource")
public class AdminResourceController {

    @NotNull
    private final AdminResourceBiz biz;

    @Operation(summary = "课程视频信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminResourcePageResp>> page(@RequestBody AdminResourcePageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "课程视频信息添加")
    @SysLog(value = "课程视频信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminResourceSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "课程视频信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminResourceViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "课程视频信息修改")
    @SysLog(value = "课程视频信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminResourceEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "课程视频信息批量移动", description = "批量移动")
    @SysLog(value = "课程视频信息批量移动")
    @PutMapping(value = "/batch/edit")
    public Result<String> batchDelete(@RequestBody @Valid AdminResourceBatchEditReq req) {
        return biz.batchEdit(req);
    }

    @Operation(summary = "课程视频信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "课程视频信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "课程视频信息批量删除", description = "批量删除")
    @SysLog(value = "课程视频信息批量删除")
    @PutMapping(value = "/batch/delete")
    public Result<String> batchDelete(@RequestBody @Valid AdminResourceDeleteReq req) {
        return biz.batchDelete(req);
    }

    @Operation(summary = "获取上传音视频参数")
    @SysLog(value = "获取上传音视频参数")
    @GetMapping(value = "/vod/config")
    public Result<AdminVodConfigResp> getVodConfig() {
        return biz.getVodConfig();
    }

    @Operation(summary = "预览", description = "获取预览参数")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "预览")
    @GetMapping(value = "/preview")
    public Result<AdminPreviewResp> preview(@RequestParam Long id) {
        return biz.preview(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "Resource"));
    }
}
