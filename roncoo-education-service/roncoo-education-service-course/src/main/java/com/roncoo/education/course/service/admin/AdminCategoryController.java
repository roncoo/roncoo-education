package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminCategoryBiz;
import com.roncoo.education.course.service.admin.req.AdminCategoryEditReq;
import com.roncoo.education.course.service.admin.req.AdminCategoryListReq;
import com.roncoo.education.course.service.admin.req.AdminCategoryPageReq;
import com.roncoo.education.course.service.admin.req.AdminCategorySaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCategoryListResp;
import com.roncoo.education.course.service.admin.resp.AdminCategoryPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCategoryViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ADMIN-分类
 *
 * @author wujing
 */
@Api(tags = "admin-分类")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/category")
public class AdminCategoryController {

    @NotNull
    private final AdminCategoryBiz biz;

    @ApiOperation(value = "分类分页", notes = "分类分页")
    @PostMapping(value = "/list")
    public Result<List<AdminCategoryListResp>> list(@RequestBody AdminCategoryListReq req) {
        return biz.list(req);
    }

    @ApiOperation(value = "分类分页", notes = "分类分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminCategoryPageResp>> page(@RequestBody AdminCategoryPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "分类添加", notes = "分类添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminCategorySaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "分类查看", notes = "分类查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminCategoryViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "分类修改", notes = "分类修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminCategoryEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "分类删除", notes = "分类删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
