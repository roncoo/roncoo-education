package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.annotation.SysLog;
import com.roncoo.education.common.annotation.SysLogCache;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.SortReq;
import com.roncoo.education.course.service.admin.biz.AdminCourseBiz;
import com.roncoo.education.course.service.admin.req.AdminCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ADMIN-课程信息
 *
 * @author wujing
 */
@Api(tags = "admin-课程信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/course")
public class AdminCourseController {

    @NotNull
    private final AdminCourseBiz biz;

    @ApiOperation(value = "课程信息分页", notes = "课程信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminCoursePageResp>> page(@RequestBody AdminCoursePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "课程信息添加", notes = "课程信息添加")
    @SysLog(value = "课程信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminCourseSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "课程信息查看", notes = "课程信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminCourseViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "课程信息修改", notes = "课程信息修改")
    @SysLog(value = "课程信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminCourseEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "课程信息删除", notes = "课程信息删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLog(value = "课程信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @ApiOperation(value = "排序", notes = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        int sort = biz.sort(req, "Course");
        return Result.success(sort);
    }
}
