package com.roncoo.education.course.service.api;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.api.biz.ApiCourseBiz;
import com.roncoo.education.course.service.api.req.ApiCoursePageReq;
import com.roncoo.education.course.service.api.req.ApiCourseReq;
import com.roncoo.education.course.service.api.resp.ApiCoursePageResp;
import com.roncoo.education.course.service.api.resp.ApiCourseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-课程信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "API-课程信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/api/course")
public class ApiCourseController {

    @NotNull
    private final ApiCourseBiz biz;

    /**
     * 课程信息列表接口
     *
     * @author fengyw
     */
    @ApiOperation(value = "列表和搜索接口", notes = "根据条件进行课程列出")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result<Page<ApiCoursePageResp>> searchForPage(@RequestBody ApiCoursePageReq req) {
        return biz.searchForPage(req);
    }

    /**
     * 课程详情接口
     */
    @ApiOperation(value = "课程详情接口", notes = "根据课程ID获取课程信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<ApiCourseResp> view(@RequestBody ApiCourseReq req) {
        return biz.view(req);
    }
}
