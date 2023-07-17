package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminLecturerBiz;
import com.roncoo.education.user.service.admin.req.AdminLecturerEditReq;
import com.roncoo.education.user.service.admin.req.AdminLecturerPageReq;
import com.roncoo.education.user.service.admin.req.AdminLecturerSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLecturerPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLecturerViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-讲师信息
 *
 * @author wujing
 */
@Api(tags = "admin-讲师信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/lecturer")
public class AdminLecturerController {

    @NotNull
    private final AdminLecturerBiz biz;

    @ApiOperation(value = "讲师信息分页", notes = "讲师信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminLecturerPageResp>> page(@RequestBody AdminLecturerPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "讲师信息添加", notes = "讲师信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminLecturerSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "讲师信息查看", notes = "讲师信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminLecturerViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "讲师信息修改", notes = "讲师信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminLecturerEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "讲师信息删除", notes = "讲师信息删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
