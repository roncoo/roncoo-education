package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.course.service.admin.biz.AdminUserCourseCommentBiz;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCommentPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCommentViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * ADMIN-课程评论
 *
 * @author wujing
 */
@Tag(name = "admin-课程评论")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/course/comment")
public class AdminUserCourseCommentController {

    @NotNull
    private final AdminUserCourseCommentBiz biz;

    @Operation(summary = "课程评论分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserCourseCommentPageResp>> page(@RequestBody AdminUserCourseCommentPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "课程评论添加")
    @SysLog(value = "课程评论添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserCourseCommentSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "课程评论查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminUserCourseCommentViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "课程评论修改")
    @SysLog(value = "课程评论修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserCourseCommentEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "课程评论删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "课程评论删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
