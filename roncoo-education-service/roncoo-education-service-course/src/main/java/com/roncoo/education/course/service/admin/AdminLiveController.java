package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminLiveBiz;
import com.roncoo.education.course.service.admin.req.AdminLiveEditReq;
import com.roncoo.education.course.service.admin.req.AdminLivePageReq;
import com.roncoo.education.course.service.admin.req.AdminLiveSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminLivePageResp;
import com.roncoo.education.course.service.admin.resp.AdminLiveViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-直播信息
 *
 * @author fengyw
 */
@Api(tags = "admin-直播信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/live")
public class AdminLiveController {

    @NotNull
    private final AdminLiveBiz biz;

    @ApiOperation(value = "讲师开播地址", notes = "获取讲师开播地址")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/broadcast")
    public Result<String> broadcast(@RequestParam Long id) {
        return biz.broadcast(id);
    }

    @ApiOperation(value = "直播信息分页", notes = "直播信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminLivePageResp>> page(@RequestBody AdminLivePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "直播信息添加", notes = "直播信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminLiveSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "直播信息查看", notes = "直播信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminLiveViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "直播信息修改", notes = "直播信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminLiveEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "直播信息删除", notes = "直播信息删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
