package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminUserCourseCollectBiz;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCollectPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCollectViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-课程收藏
 *
 * @author wujing
 */
@Tag(name = "admin-课程收藏")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/course/collect")
public class AdminUserCourseCollectController {

    @NotNull
    private final AdminUserCourseCollectBiz biz;

    @Operation(summary = "课程收藏分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserCourseCollectPageResp>> page(@RequestBody AdminUserCourseCollectPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "课程收藏添加")
    @SysLog(value = "课程收藏添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserCourseCollectSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "课程收藏查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUserCourseCollectViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "课程收藏修改")
    @SysLog(value = "课程收藏修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserCourseCollectEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "课程收藏删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "课程收藏删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
