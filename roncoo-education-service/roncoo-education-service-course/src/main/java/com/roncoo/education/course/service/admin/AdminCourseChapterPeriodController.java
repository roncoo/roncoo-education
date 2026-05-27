package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminCourseChapterPeriodBiz;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodEditReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodListReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodPageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-课时信息
 *
 * @author wujing
 */
@Tag(name = "admin-课时信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/course/chapter/period")
public class AdminCourseChapterPeriodController {

    @NotNull
    private final AdminCourseChapterPeriodBiz biz;

    @Operation(summary = "课时信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminCourseChapterPeriodPageResp>> page(@RequestBody AdminCourseChapterPeriodPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "课时信息列表")
    @PostMapping(value = "/list")
    public Result<List<AdminCourseChapterPeriodViewResp>> list(@RequestBody AdminCourseChapterPeriodListReq req) {
        return biz.list(req);
    }

    @Operation(summary = "课时信息添加")
    @SysLog(value = "课时信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminCourseChapterPeriodSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "课时信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminCourseChapterPeriodViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "课时信息修改")
    @SysLog(value = "课时信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminCourseChapterPeriodEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "课时信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "课时信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "CourseChapterPeriod"));
    }
}
