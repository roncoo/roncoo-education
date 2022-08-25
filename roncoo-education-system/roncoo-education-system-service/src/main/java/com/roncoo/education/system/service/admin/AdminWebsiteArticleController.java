package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteArticleBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteArticleEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteArticlePageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteArticleSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteArticlePageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteArticleViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-站点导航文章
 *
 * @author wujing
 */
@Api(tags = "ADMIN-站点导航文章")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/article")
public class AdminWebsiteArticleController {

    @NotNull
    private final AdminWebsiteArticleBiz biz;

    @ApiOperation(value = "站点导航文章分页", notes = "站点导航文章分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteArticlePageResp>> page(@RequestBody AdminWebsiteArticlePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "站点导航文章添加", notes = "站点导航文章添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteArticleSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "站点导航文章查看", notes = "站点导航文章查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminWebsiteArticleViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "站点导航文章修改", notes = "站点导航文章修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteArticleEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "站点导航文章删除", notes = "站点导航文章删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
