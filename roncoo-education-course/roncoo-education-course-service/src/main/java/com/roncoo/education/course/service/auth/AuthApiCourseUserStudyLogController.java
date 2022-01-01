package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthApiCourseUserStudyLogBiz;
import com.roncoo.education.course.service.auth.bo.AuthCourseUserStudyLogPageBO;
import com.roncoo.education.course.service.auth.dto.AuthCourseUserStudyLogPageDTO;
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
@RequestMapping(value = "/course/auth/course/user/study/log")
public class AuthApiCourseUserStudyLogController extends BaseController {

    @Autowired
    private AuthApiCourseUserStudyLogBiz biz;

    /**
     * 最近学习日志分页列出接口
     *
     * @param authCourseUserStudyLogPageBO
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "分页列出接口", notes = "最近学习分页列出接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<AuthCourseUserStudyLogPageDTO>> list(@RequestBody AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO) {
        return biz.list(authCourseUserStudyLogPageBO);
    }

}
