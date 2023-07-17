package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminUserStudyBiz;
import com.roncoo.education.course.service.admin.req.AdminUserStudyEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudyPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudySaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-课程用户学习日志
 *
 * @author wujing
 */
@Api(tags = "admin-课程用户学习日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/study")
public class AdminUserStudyController {

    @NotNull
    private final AdminUserStudyBiz biz;

    @ApiOperation(value = "课程用户学习日志分页", notes = "课程用户学习日志分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserStudyPageResp>> page(@RequestBody AdminUserStudyPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "课程用户学习日志添加", notes = "课程用户学习日志添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserStudySaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "课程用户学习日志查看", notes = "课程用户学习日志查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminUserStudyViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "课程用户学习日志修改", notes = "课程用户学习日志修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserStudyEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "课程用户学习日志删除", notes = "课程用户学习日志删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
