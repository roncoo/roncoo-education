package com.roncoo.education.course.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.api.biz.ApiUserStudyBiz;
import com.roncoo.education.course.service.auth.req.AuthUserStudyReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-课程用户学习日志
 *
 * @author wujing
 * @date 2022-09-03
 */
@Api(tags = "api-课程用户学习日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/api/user/study")
public class ApiUserStudyController {

    @NotNull
    private final ApiUserStudyBiz biz;

    /**
     * 课程信息列表接口
     *
     * @author fengyw
     */
    @ApiOperation(value = "记录学习进度", notes = "记录学习进度")
    @RequestMapping(value = "/progress", method = RequestMethod.POST)
    public Result<String> study(@RequestBody AuthUserStudyReq req) {
        return biz.study(req);
    }

}
