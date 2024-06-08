package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.annotation.SysLog;
import com.roncoo.education.common.annotation.SysLogCache;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminUserStudyBiz;
import com.roncoo.education.course.service.admin.req.AdminUserStudyEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudyPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudySaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyStatResp;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-资源学习记录
 *
 * @author wujing
 */
@Api(tags = "admin-资源学习记录")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/study")
public class AdminUserStudyController {

    @NotNull
    private final AdminUserStudyBiz biz;

    @ApiOperation(value = "资源学习记录分页", notes = "资源学习记录分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserStudyPageResp>> page(@RequestBody AdminUserStudyPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "资源学习记录添加", notes = "资源学习记录添加")
    @SysLog(value = "资源学习记录添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserStudySaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "资源学习记录查看", notes = "资源学习记录查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUserStudyViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "资源学习记录修改", notes = "资源学习记录修改")
    @SysLog(value = "资源学习记录修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserStudyEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "资源学习记录删除", notes = "资源学习记录删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @SysLog(value = "资源学习记录删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @ApiOperation(value = "课程用户学习统计", notes = "课程用户学习统计")
    @ApiImplicitParam(name = "userId", value = "用户ID", dataTypeClass = Long.class, paramType = "query", required = false)
    @GetMapping(value = "/stat")
    public Result<AdminUserStudyStatResp> stat(@RequestParam Long userId) {
        return biz.stat(userId);
    }

}
