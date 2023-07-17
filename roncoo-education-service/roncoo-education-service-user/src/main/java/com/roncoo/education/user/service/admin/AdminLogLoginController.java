package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminLogLoginBiz;
import com.roncoo.education.user.service.admin.req.AdminLogLoginEditReq;
import com.roncoo.education.user.service.admin.req.AdminLogLoginPageReq;
import com.roncoo.education.user.service.admin.req.AdminLogLoginSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLogLoginPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLogLoginViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-用户登录日志
 *
 * @author wujing
 */
@Api(tags = "admin-用户登录日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/log/login")
public class AdminLogLoginController {

    @NotNull
    private final AdminLogLoginBiz biz;

    @ApiOperation(value = "用户登录日志分页", notes = "用户登录日志分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminLogLoginPageResp>> page(@RequestBody AdminLogLoginPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "用户登录日志添加", notes = "用户登录日志添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminLogLoginSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "用户登录日志查看", notes = "用户登录日志查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminLogLoginViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "用户登录日志修改", notes = "用户登录日志修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminLogLoginEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "用户登录日志删除", notes = "用户登录日志删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
