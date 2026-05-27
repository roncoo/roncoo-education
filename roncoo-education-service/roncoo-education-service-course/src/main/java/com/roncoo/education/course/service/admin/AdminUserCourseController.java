package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminUserCourseBiz;
import com.roncoo.education.course.service.admin.req.AdminUserCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseRecordReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseRecordResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-课程用户关联表
 *
 * @author wujing
 */
@Tag(name = "admin-课程用户关联表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/course")
public class AdminUserCourseController {

    @NotNull
    private final AdminUserCourseBiz biz;

    @Operation(summary = "课程用户关联表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserCoursePageResp>> page(@RequestBody AdminUserCoursePageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "课程用户关联表记录")
    @PostMapping(value = "/record")
    public Result<Page<AdminUserCourseRecordResp>> record(@RequestBody AdminUserCourseRecordReq req) {
        return biz.record(req);
    }

    @Operation(summary = "课程用户关联表添加")
    @SysLog(value = "课程用户关联表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserCourseSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "课程用户关联表查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUserCourseViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "课程用户关联表修改")
    @SysLog(value = "课程用户关联表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserCourseEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "课程用户关联表删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "课程用户关联表删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
