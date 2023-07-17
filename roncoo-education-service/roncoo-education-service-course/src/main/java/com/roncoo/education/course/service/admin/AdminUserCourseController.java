package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminUserCourseBiz;
import com.roncoo.education.course.service.admin.req.AdminUserCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseRecordReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseRecordResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-课程用户关联表
 *
 * @author wujing
 */
@Api(tags = "admin-课程用户关联表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/course")
public class AdminUserCourseController {

    @NotNull
    private final AdminUserCourseBiz biz;

    @ApiOperation(value = "课程用户关联表分页", notes = "课程用户关联表分页")
    @PostMapping(value = "/record")
    public Result<Page<AdminUserCourseRecordResp>> record(@RequestBody AdminUserCourseRecordReq req) {
        return biz.record(req);
    }

    @ApiOperation(value = "课程用户关联表分页", notes = "课程用户关联表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserCoursePageResp>> page(@RequestBody AdminUserCoursePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "课程用户关联表添加", notes = "课程用户关联表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserCourseSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "课程用户关联表查看", notes = "课程用户关联表查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminUserCourseViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "课程用户关联表修改", notes = "课程用户关联表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserCourseEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "课程用户关联表删除", notes = "课程用户关联表删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
