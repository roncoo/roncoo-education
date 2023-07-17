package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminResourceBiz;
import com.roncoo.education.course.service.admin.req.AdminResourceEditReq;
import com.roncoo.education.course.service.admin.req.AdminResourcePageReq;
import com.roncoo.education.course.service.admin.req.AdminResourceSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminResourcePageResp;
import com.roncoo.education.course.service.admin.resp.AdminResourceViewResp;
import com.roncoo.education.course.service.admin.resp.AdminVodConfigResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-课程视频信息
 *
 * @author wujing
 */
@Api(tags = "admin-课程视频信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/resource")
public class AdminResourceController {

    @NotNull
    private final AdminResourceBiz biz;

    @ApiOperation(value = "获取上传音视频参数", notes = "获取上传音视频参数")
    @GetMapping(value = "/vod/config")
    public Result<AdminVodConfigResp> getVodConfig() {
        return biz.getVodConfig();
    }

    @ApiOperation(value = "课程视频信息分页", notes = "课程视频信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminResourcePageResp>> page(@RequestBody AdminResourcePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "课程视频信息添加", notes = "课程视频信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminResourceSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "课程视频信息查看", notes = "课程视频信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminResourceViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "课程视频信息修改", notes = "课程视频信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminResourceEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "课程视频信息删除", notes = "课程视频信息删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
