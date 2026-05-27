package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminOrderPayBiz;
import com.roncoo.education.user.service.admin.req.AdminOrderPayEditReq;
import com.roncoo.education.user.service.admin.req.AdminOrderPayPageReq;
import com.roncoo.education.user.service.admin.req.AdminOrderPaySaveReq;
import com.roncoo.education.user.service.admin.resp.AdminOrderPayPageResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderPayViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-订单支付信息表
 *
 * @author wujing
 */
@Tag(name = "admin-订单支付信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/order/pay")
public class AdminOrderPayController {

    @NotNull
    private final AdminOrderPayBiz biz;

    @Operation(summary = "订单支付信息表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminOrderPayPageResp>> page(@RequestBody AdminOrderPayPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "订单支付信息表添加")
    @SysLog(value = "订单支付信息表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminOrderPaySaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "订单支付信息表查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminOrderPayViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "订单支付信息表修改")
    @SysLog(value = "订单支付信息表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminOrderPayEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "订单支付信息表删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "订单支付信息表删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
