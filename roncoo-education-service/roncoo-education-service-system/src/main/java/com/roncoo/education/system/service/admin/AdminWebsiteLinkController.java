package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteLinkBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteLinkPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteLinkViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-站点友情链接
 *
 * @author wujing
 */
@Api(tags = "admin-站点友情链接")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/link")
public class AdminWebsiteLinkController {

    @NotNull
    private final AdminWebsiteLinkBiz biz;

    @ApiOperation(value = "站点友情链接分页", notes = "站点友情链接分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteLinkPageResp>> page(@RequestBody AdminWebsiteLinkPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "站点友情链接添加", notes = "站点友情链接添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteLinkSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "站点友情链接查看", notes = "站点友情链接查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminWebsiteLinkViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "站点友情链接修改", notes = "站点友情链接修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteLinkEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "站点友情链接删除", notes = "站点友情链接删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
