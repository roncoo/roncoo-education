package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteAdvBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAdvEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAdvPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAdvSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAdvPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAdvViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-广告信息
 *
 * @author wujing
 */
@Api(tags = "ADMIN-广告信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/adv")
public class AdminWebsiteAdvController {

    @NotNull
    private final AdminWebsiteAdvBiz biz;

    @ApiOperation(value = "广告信息分页", notes = "广告信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteAdvPageResp>> page(@RequestBody AdminWebsiteAdvPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "广告信息添加", notes = "广告信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteAdvSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "广告信息查看", notes = "广告信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminWebsiteAdvViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "广告信息修改", notes = "广告信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteAdvEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "广告信息删除", notes = "广告信息删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
