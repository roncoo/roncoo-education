package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminCourseChapterBiz;
import com.roncoo.education.course.service.admin.req.*;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-章节信息
 *
 * @author wujing
 */
@Tag(name = "admin-章节信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/course/chapter")
public class AdminCourseChapterController {

    @NotNull
    private final AdminCourseChapterBiz biz;

    @Operation(summary = "章节信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminCourseChapterPageResp>> page(@RequestBody AdminCourseChapterPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "章节信息列表")
    @PostMapping(value = "/list")
    public Result<List<AdminCourseChapterViewResp>> list(@RequestBody AdminCourseChapterListReq req) {
        return biz.list(req);
    }

    @Operation(summary = "章节信息添加")
    @SysLog(value = "章节信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminCourseChapterSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "章节信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminCourseChapterViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "章节信息修改")
    @SysLog(value = "章节信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminCourseChapterEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "章节信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "章节信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<String> sort(@RequestBody @Valid List<AdminCourseChapterSortReq> req) {
        return biz.sort(req);
    }
}
