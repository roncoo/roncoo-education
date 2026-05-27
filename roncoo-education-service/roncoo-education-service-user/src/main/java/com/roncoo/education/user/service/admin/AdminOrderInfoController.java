package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminOrderInfoBiz;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoEditReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoPageReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoPageResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoStatResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-订单信息表
 *
 * @author wujing
 */
@Tag(name = "admin-订单信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/order/info")
public class AdminOrderInfoController {

    @NotNull
    private final AdminOrderInfoBiz biz;

    @Operation(summary = "订单信息表分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminOrderInfoPageResp>> page(@RequestBody AdminOrderInfoPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "订单信息表添加")
    @SysLog(value = "订单信息表添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminOrderInfoSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "订单信息表查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminOrderInfoViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "订单信息表修改")
    @SysLog(value = "订单信息表修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminOrderInfoEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "订单信息表删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "订单信息表删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "订单信息表统计")
    @Parameter(name = "userId", description = "用户ID", required = false)
    @GetMapping(value = "/stat")
    public Result<AdminOrderInfoStatResp> stat(@RequestParam Long userId) {
        return biz.stat(userId);
    }

}
