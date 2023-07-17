package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminMsgBiz;
import com.roncoo.education.user.service.admin.req.AdminMsgEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgPageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgPageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-站内信息表
 *
 * @author wujing
 */
@Api(tags = "admin-站内信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/msg")
public class AdminMsgController {

    @NotNull
    private final AdminMsgBiz biz;

    @ApiOperation(value = "站内信息表分页", notes = "站内信息表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminMsgPageResp>> page(@RequestBody AdminMsgPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "站内信息表添加", notes = "站内信息表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminMsgSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "站内信息表查看", notes = "站内信息表查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminMsgViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "站内信息表修改", notes = "站内信息表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminMsgEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "站内信息表删除", notes = "站内信息表删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
