package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
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
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminCourseSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "课程信息查看", notes = "课程信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminCourseViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "课程信息修改", notes = "课程信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminCourseEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "课程信息删除", notes = "课程信息删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @ApiOperation(value = "ES同步", notes = "将课程同步到ES")
    @GetMapping(value = "/es")
    public Result<String> syncEs() {
        return biz.syncEs();
    }
}
