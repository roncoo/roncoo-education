package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminUserStudyBiz;
import com.roncoo.education.course.service.admin.req.AdminUserStudyEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudyPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudySaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyStatResp;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-资源学习记录
 *
 * @author wujing
 */
@Tag(name = "admin-资源学习记录")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/study")
public class AdminUserStudyController {

    @NotNull
    private final AdminUserStudyBiz biz;

    @Operation(summary = "资源学习记录分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserStudyPageResp>> page(@RequestBody AdminUserStudyPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "资源学习记录添加")
    @SysLog(value = "资源学习记录添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserStudySaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "资源学习记录查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUserStudyViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "资源学习记录修改")
    @SysLog(value = "资源学习记录修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserStudyEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "资源学习记录删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "资源学习记录删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "课程用户学习统计")
    @Parameter(name = "userId", description = "用户ID", required = false)
    @GetMapping(value = "/stat")
    public Result<AdminUserStudyStatResp> stat(@RequestParam Long userId) {
        return biz.stat(userId);
    }

}
