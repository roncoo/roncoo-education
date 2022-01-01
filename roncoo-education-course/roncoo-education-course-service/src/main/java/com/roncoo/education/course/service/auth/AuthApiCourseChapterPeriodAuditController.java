package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthApiCourseChapterPeriodAuditBiz;
import com.roncoo.education.course.service.auth.bo.*;
import com.roncoo.education.course.service.auth.dto.AuthCourseChapterPeriodAuditSaveDTO;
import com.roncoo.education.course.service.auth.dto.AuthPeriodAuditListDTO;
import com.roncoo.education.course.service.auth.dto.AuthPeriodAuditViewDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/auth/course/chapter/period/audit")
public class AuthApiCourseChapterPeriodAuditController extends BaseController {

    @Autowired
    private AuthApiCourseChapterPeriodAuditBiz biz;

    /**
     * 课时列出接口
     */
    @ApiOperation(value = "课时列出接口", notes = "根据章节ID列出课时信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<AuthPeriodAuditListDTO> listByChapterId(@RequestBody AuthCourseChapterPeriodAuditBO authCourseChapterPeriodAuditBO) {
        return biz.listByChapterId(authCourseChapterPeriodAuditBO);
    }

    /**
     * 课时查看接口
     */
    @ApiOperation(value = "课时查看接口", notes = "课时查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AuthPeriodAuditViewDTO> view(@RequestBody AuthCourseChapterPeriodAuditViewBO authCourseChapterPeriodAuditViewBO) {
        return biz.view(authCourseChapterPeriodAuditViewBO);
    }

    /**
     * 课时删除接口
     */
    @ApiOperation(value = "课时删除接口", notes = "课时删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody AuthCourseChapterPeriodAuditDeleteBO authCourseChapterPeriodAuditDeleteBO) {
        return biz.delete(authCourseChapterPeriodAuditDeleteBO);
    }

    /**
     * 课时添加接口
     */
    @ApiOperation(value = "课时添加接口", notes = "课时添加接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<AuthCourseChapterPeriodAuditSaveDTO> save(@RequestBody AuthCourseChapterPeriodAuditSaveBO authCourseChapterPeriodAuditSaveBO) {
        return biz.save(authCourseChapterPeriodAuditSaveBO);
    }

    /**
     * 课时更新接口
     */
    @ApiOperation(value = "课时更新接口", notes = "课时更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody AuthCourseChapterPeriodAuditUpdateBO authCourseChapterPeriodAuditUpdateBO) {
        return biz.update(authCourseChapterPeriodAuditUpdateBO);
    }

    /**
     * 课时排序接口
     */
    @ApiOperation(value = "更新课时排序", notes = "更新课时排序")
    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public Result<Integer> sort(@RequestBody AuthCourseChapterPeriodAuditSortBO authCourseChapterPeriodAuditSortBO) {
        return biz.sort(authCourseChapterPeriodAuditSortBO);
    }

}
