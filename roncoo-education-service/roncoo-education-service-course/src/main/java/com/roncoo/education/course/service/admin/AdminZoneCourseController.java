package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminZoneCourseBiz;
import com.roncoo.education.course.service.admin.req.AdminZoneCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminZoneCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminZoneCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminZoneCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminZoneCourseViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-专区课程关联表
 *
 * @author wujing
 */
@Api(tags = "admin-专区课程关联表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/zone/course")
public class AdminZoneCourseController {

    @NotNull
    private final AdminZoneCourseBiz biz;

    @ApiOperation(value = "专区课程关联表分页", notes = "专区课程关联表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminZoneCoursePageResp>> page(@RequestBody AdminZoneCoursePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "专区课程关联表添加", notes = "专区课程关联表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminZoneCourseSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "专区课程关联表查看", notes = "专区课程关联表查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminZoneCourseViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "专区课程关联表修改", notes = "专区课程关联表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminZoneCourseEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "专区课程关联表删除", notes = "专区课程关联表删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
