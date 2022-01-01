package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthApiCourseAuditBiz;
import com.roncoo.education.course.service.auth.bo.*;
import com.roncoo.education.course.service.auth.dto.AuthCourseAuditListDTO;
import com.roncoo.education.course.service.auth.dto.AuthCourseAuditSaveDTO;
import com.roncoo.education.course.service.auth.dto.AuthCourseAuditViewDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/auth/course/audit")
public class AuthApiCourseAuditController extends BaseController {

    @Autowired
    private AuthApiCourseAuditBiz biz;

    /**
     * 讲师课程详情接口
     */
    @ApiOperation(value = "课程详情接口", notes = "根据课程ID返回课程详情信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AuthCourseAuditViewDTO> view(@RequestBody AuthCourseAuditViewBO authCourseAuditViewBO) {
        return biz.view(authCourseAuditViewBO);
    }

    /**
     * 讲师课程分页接口
     */
    @ApiOperation(value = "课程分页接口", notes = "根据条件分页列出课程信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<AuthCourseAuditListDTO>> listForPage(@RequestBody AuthCourseAuditListBO authCourseAuditListBO) {
        return biz.listForPage(authCourseAuditListBO);
    }

    /**
     * 讲师课程保存接口
     */
    @ApiOperation(value = "课程保存接口", notes = "课程保存接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<AuthCourseAuditSaveDTO> save(@RequestBody AuthCourseAuditSaveBO authCourseAuditSaveBO) {
        return biz.save(authCourseAuditSaveBO);
    }

    /**
     * 讲师课程更新接口
     */
    @ApiOperation(value = "课程更新接口", notes = "课程更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody AuthCourseAuditUpdateBO authCourseAuditUpdateBO) {
        return biz.update(authCourseAuditUpdateBO);
    }

    /**
     * 讲师课程信息设置上下架接口
     */
    @ApiOperation(value = "课程上下架接口", notes = "课程上下架接口")
    @RequestMapping(value = "/stand", method = RequestMethod.POST)
    public Result<Integer> stand(@RequestBody AuthCourseAuditStandBO authCourseAuditStandBO) {
        return biz.stand(authCourseAuditStandBO);
    }

    /**
     * 讲师课程信息删除接口
     *
     * @author wuyun
     */
    @ApiOperation(value = "删除接口", notes = "讲师课程信息删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody AuthCourseAuditDeleteBO authCourseAuditDeleteBO) {
        return biz.delete(authCourseAuditDeleteBO);
    }

}
