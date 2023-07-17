package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminOrderPayBiz;
import com.roncoo.education.user.service.admin.req.AdminOrderPayEditReq;
import com.roncoo.education.user.service.admin.req.AdminOrderPayPageReq;
import com.roncoo.education.user.service.admin.req.AdminOrderPaySaveReq;
import com.roncoo.education.user.service.admin.resp.AdminOrderPayPageResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderPayViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-订单支付信息表
 *
 * @author wujing
 */
@Api(tags = "admin-订单支付信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/order/pay")
public class AdminOrderPayController {

    @NotNull
    private final AdminOrderPayBiz biz;

    @ApiOperation(value = "订单支付信息表分页", notes = "订单支付信息表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminOrderPayPageResp>> page(@RequestBody AdminOrderPayPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "订单支付信息表添加", notes = "订单支付信息表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminOrderPaySaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "订单支付信息表查看", notes = "订单支付信息表查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminOrderPayViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "订单支付信息表修改", notes = "订单支付信息表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminOrderPayEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "订单支付信息表删除", notes = "订单支付信息表删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
