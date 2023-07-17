package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminRegionBiz;
import com.roncoo.education.user.service.admin.req.AdminRegionEditReq;
import com.roncoo.education.user.service.admin.req.AdminRegionPageReq;
import com.roncoo.education.user.service.admin.req.AdminRegionSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminRegionPageResp;
import com.roncoo.education.user.service.admin.resp.AdminRegionViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-行政区域表
 *
 * @author wujing
 */
@Api(tags = "admin-行政区域表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/region")
public class AdminRegionController {

    @NotNull
    private final AdminRegionBiz biz;

    @ApiOperation(value = "行政区域表分页", notes = "行政区域表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminRegionPageResp>> page(@RequestBody AdminRegionPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "行政区域表添加", notes = "行政区域表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminRegionSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "行政区域表查看", notes = "行政区域表查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminRegionViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "行政区域表修改", notes = "行政区域表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminRegionEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "行政区域表删除", notes = "行政区域表删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
