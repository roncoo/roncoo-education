package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminOrderInfoBiz;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoEditReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoPageReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoPageResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-订单信息表
 *
 * @author wujing
 */
@Api(tags = "admin-订单信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/order/info")
public class AdminOrderInfoController {

    @NotNull
    private final AdminOrderInfoBiz biz;

    @ApiOperation(value = "订单信息表分页", notes = "订单信息表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminOrderInfoPageResp>> page(@RequestBody AdminOrderInfoPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "订单信息表添加", notes = "订单信息表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminOrderInfoSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "订单信息表查看", notes = "订单信息表查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminOrderInfoViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "订单信息表修改", notes = "订单信息表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminOrderInfoEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "订单信息表删除", notes = "订单信息表删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
