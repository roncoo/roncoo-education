package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminUserCourseCollectBiz;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCollectPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCollectViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-课程收藏
 *
 * @author wujing
 */
@Api(tags = "admin-课程收藏")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/course/collect")
public class AdminUserCourseCollectController {

    @NotNull
    private final AdminUserCourseCollectBiz biz;

    @ApiOperation(value = "课程收藏分页", notes = "课程收藏分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserCourseCollectPageResp>> page(@RequestBody AdminUserCourseCollectPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "课程收藏添加", notes = "课程收藏添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserCourseCollectSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "课程收藏查看", notes = "课程收藏查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminUserCourseCollectViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "课程收藏修改", notes = "课程收藏修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserCourseCollectEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "课程收藏删除", notes = "课程收藏删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
