package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteNavBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteNavPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteNavViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-头部导航
 *
 * @author wujing
 */
@Api(tags = "admin-头部导航")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/nav")
public class AdminWebsiteNavController {

    @NotNull
    private final AdminWebsiteNavBiz biz;

    @ApiOperation(value = "头部导航分页", notes = "头部导航分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteNavPageResp>> page(@RequestBody AdminWebsiteNavPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "头部导航添加", notes = "头部导航添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteNavSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "头部导航查看", notes = "头部导航查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminWebsiteNavViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "头部导航修改", notes = "头部导航修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteNavEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "头部导航删除", notes = "头部导航删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
