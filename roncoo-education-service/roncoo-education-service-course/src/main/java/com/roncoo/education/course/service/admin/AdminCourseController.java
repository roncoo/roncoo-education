package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminCourseBiz;
import com.roncoo.education.course.service.admin.req.AdminCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-课程信息
 *
 * @author wujing
 */
@Tag(name = "admin-课程信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/course")
public class AdminCourseController {

    @NotNull
    private final AdminCourseBiz biz;

    @Operation(summary = "课程信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminCoursePageResp>> page(@RequestBody AdminCoursePageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "课程信息添加")
    @SysLog(value = "课程信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminCourseSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "课程信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminCourseViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "课程信息修改")
    @SysLog(value = "课程信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminCourseEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "课程信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "课程信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        int sort = biz.sort(req, "Course");
        return Result.success(sort);
    }
}
