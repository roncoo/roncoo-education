package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminLogSmsBiz;
import com.roncoo.education.user.service.admin.req.AdminLogSmsEditReq;
import com.roncoo.education.user.service.admin.req.AdminLogSmsPageReq;
import com.roncoo.education.user.service.admin.req.AdminLogSmsSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLogSmsPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLogSmsViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-用户短信发送日志
 *
 * @author wujing
 */
@Api(tags = "ADMIN-用户短信发送日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/log/sms")
public class AdminLogSmsController {

    @NotNull
    private final AdminLogSmsBiz biz;

    @ApiOperation(value = "用户短信发送日志分页", notes = "用户短信发送日志分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminLogSmsPageResp>> page(@RequestBody AdminLogSmsPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "用户短信发送日志添加", notes = "用户短信发送日志添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminLogSmsSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "用户短信发送日志查看", notes = "用户短信发送日志查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminLogSmsViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "用户短信发送日志修改", notes = "用户短信发送日志修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminLogSmsEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "用户短信发送日志删除", notes = "用户短信发送日志删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
