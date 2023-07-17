package com.roncoo.education.course.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.admin.biz.AdminUserCourseCommentBiz;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCommentPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCommentViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-课程评论
 *
 * @author wujing
 */
@Api(tags = "admin-课程评论")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/admin/user/course/comment")
public class AdminUserCourseCommentController {

    @NotNull
    private final AdminUserCourseCommentBiz biz;

    @ApiOperation(value = "课程评论分页", notes = "课程评论分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminUserCourseCommentPageResp>> page(@RequestBody AdminUserCourseCommentPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "课程评论添加", notes = "课程评论添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminUserCourseCommentSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "课程评论查看", notes = "课程评论查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminUserCourseCommentViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "课程评论修改", notes = "课程评论修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminUserCourseCommentEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "课程评论删除", notes = "课程评论删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
