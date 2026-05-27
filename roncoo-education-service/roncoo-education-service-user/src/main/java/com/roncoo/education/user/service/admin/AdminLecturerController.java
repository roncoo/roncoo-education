package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.user.service.admin.biz.AdminLecturerBiz;
import com.roncoo.education.user.service.admin.req.AdminLecturerEditReq;
import com.roncoo.education.user.service.admin.req.AdminLecturerPageReq;
import com.roncoo.education.user.service.admin.req.AdminLecturerSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLecturerPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLecturerViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-讲师信息
 *
 * @author wujing
 */
@Tag(name = "admin-讲师信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/lecturer")
public class AdminLecturerController {

    @NotNull
    private final AdminLecturerBiz biz;

    @Operation(summary = "讲师信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminLecturerPageResp>> page(@RequestBody AdminLecturerPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "讲师信息添加")
    @SysLog(value = "讲师信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminLecturerSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "讲师信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminLecturerViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "讲师信息修改")
    @SysLog(value = "讲师信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminLecturerEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "讲师信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "讲师信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "Lecturer"));
    }
}
