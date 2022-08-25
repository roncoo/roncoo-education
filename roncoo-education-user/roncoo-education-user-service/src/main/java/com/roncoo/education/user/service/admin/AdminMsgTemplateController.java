package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.base.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminMsgTemplateBiz;
import com.roncoo.education.user.service.admin.req.AdminMsgTemplateEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgTemplatePageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgTemplateSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgTemplatePageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgTemplateViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-消息模板
 *
 * @author wujing
 */
@Api(tags = "ADMIN-消息模板")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/msg/template")
public class AdminMsgTemplateController {

    @NotNull
    private final AdminMsgTemplateBiz biz;

    @ApiOperation(value = "消息模板分页", notes = "消息模板分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminMsgTemplatePageResp>> page(@RequestBody AdminMsgTemplatePageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "消息模板添加", notes = "消息模板添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminMsgTemplateSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "消息模板查看", notes = "消息模板查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminMsgTemplateViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "消息模板修改", notes = "消息模板修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminMsgTemplateEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "消息模板删除", notes = "消息模板删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
