package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminLiveBiz;
import com.roncoo.education.course.service.admin.req.AdminLiveBroadcastReq;
import com.roncoo.education.course.service.admin.req.AdminLiveEditReq;
import com.roncoo.education.course.service.admin.req.AdminLivePageReq;
import com.roncoo.education.course.service.admin.req.AdminLiveSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminLivePageResp;
import com.roncoo.education.course.service.admin.resp.AdminLiveViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-直播信息
 *
 * @author fengyw
 */
@Tag(name = "admin-直播信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/live")
public class AdminLiveController {

    @NotNull
    private final AdminLiveBiz biz;

    @Operation(summary = "讲师开播地址", description = "获取讲师开播地址")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PostMapping(value = "/broadcast")
    public Result<String> broadcast(@RequestBody AdminLiveBroadcastReq req) {
        return biz.broadcast(req);
    }

    @Operation(summary = "直播信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminLivePageResp>> page(@RequestBody AdminLivePageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "直播信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminLiveSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "直播信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @GetMapping(value = "/view")
    public Result<AdminLiveViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "直播信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminLiveEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "直播信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
