package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminUsersBiz;
import com.roncoo.education.user.service.admin.req.AdminUsersEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-用户信息
 *
 * @author wujing
 */
@Api(tags = "admin-用户信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/users")
public class AdminUsersController {

    @NotNull
    private final AdminUsersBiz biz;

    @ApiOperation(value = "用户信息分页", notes = "用户信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUsersPageResp>> page(@RequestBody AdminUsersPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "用户信息添加", notes = "用户信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUsersSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "用户信息查看", notes = "用户信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminUsersViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "用户信息修改", notes = "用户信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUsersEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "用户信息删除", notes = "用户信息删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
