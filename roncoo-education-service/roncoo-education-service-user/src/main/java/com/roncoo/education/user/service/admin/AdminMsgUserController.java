package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminMsgUserBiz;
import com.roncoo.education.user.service.admin.req.AdminMsgUserEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgUserPageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgUserSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgUserPageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgUserViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-站内信用户记录表
 *
 * @author wujing
 */
@Api(tags = "admin-站内信用户记录表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/msg/user")
public class AdminMsgUserController {

    @NotNull
    private final AdminMsgUserBiz biz;

    @ApiOperation(value = "站内信用户记录表分页", notes = "站内信用户记录表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminMsgUserPageResp>> page(@RequestBody AdminMsgUserPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "站内信用户记录表添加", notes = "站内信用户记录表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminMsgUserSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "站内信用户记录表查看", notes = "站内信用户记录表查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminMsgUserViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "站内信用户记录表修改", notes = "站内信用户记录表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminMsgUserEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "站内信用户记录表删除", notes = "站内信用户记录表删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
