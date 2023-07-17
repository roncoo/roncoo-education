package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminZoneBiz;
import com.roncoo.education.course.service.admin.req.AdminZoneEditReq;
import com.roncoo.education.course.service.admin.req.AdminZonePageReq;
import com.roncoo.education.course.service.admin.req.AdminZoneSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminZonePageResp;
import com.roncoo.education.course.service.admin.resp.AdminZoneViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-专区
 *
 * @author wujing
 */
@Api(tags = "admin-专区")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/zone")
public class AdminZoneController {

    @NotNull
    private final AdminZoneBiz biz;

    @ApiOperation(value = "专区分页", notes = "专区分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminZonePageResp>> page(@RequestBody AdminZonePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "专区添加", notes = "专区添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminZoneSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "专区查看", notes = "专区查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminZoneViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "专区修改", notes = "专区修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminZoneEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "专区删除", notes = "专区删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
