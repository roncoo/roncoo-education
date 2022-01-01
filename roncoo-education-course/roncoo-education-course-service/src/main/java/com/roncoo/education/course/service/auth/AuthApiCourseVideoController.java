package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthApiCourseVideoBiz;
import com.roncoo.education.course.service.auth.bo.*;
import com.roncoo.education.course.service.auth.dto.AuthCourseVideoListDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/course/auth/course/video")
public class AuthApiCourseVideoController extends BaseController {

    @Autowired
    private AuthApiCourseVideoBiz biz;

    /**
     * 章节视频库，课时视频库添加接口
     *
     * @param authCourseVideoSaveBo
     * @author wuyun
     */
    @ApiOperation(value = "讲师视频库保存接口", notes = "讲师视频库保存接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody AuthCourseVideoSaveBO authCourseVideoSaveBO) {
        return biz.save(authCourseVideoSaveBO);
    }

    /**
     * 章节视频库列出（右边的展示）
     *
     * @param chapterId
     * @author wuyun
     */
    @ApiOperation(value = "章节视频库列出接口", notes = "根据章节ID列出讲师章节视频库信息")
    @RequestMapping(value = "/list/chapter", method = RequestMethod.POST)
    public Result<AuthCourseVideoListDTO> listByChapterId(@RequestBody AuthCourseVideoBO authCourseVideoBO) {
        return biz.listByChapterId(authCourseVideoBO);
    }

    /**
     * 课时视频列出（左边的展示）
     *
     * @param periodId
     * @author wuyun
     */
    @ApiOperation(value = "课时视频列出接口", notes = "根据课时ID列出讲师课时视频信息")
    @RequestMapping(value = "/list/period", method = RequestMethod.POST)
    public Result<AuthCourseVideoListDTO> listByPeriodId(@RequestBody AuthPeriodIdVideoBO authCourseVideoBO) {
        return biz.listByPeriodId(authCourseVideoBO);
    }

    /**
     * 课时视频更新接口
     *
     * @param authCourseVideoUpdateBo
     * @author wuyun
     */
    @ApiOperation(value = "课时视频更新接口", notes = "讲师课时视频更新信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody AuthCourseVideoUpdateBO authCourseVideoUpdateBO) {
        return biz.update(authCourseVideoUpdateBO);
    }

    /**
     * 视频删除
     *
     * @param authCourseVideoDeleteBO
     * @author wuyun
     */
    @ApiOperation(value = "删除接口", notes = "讲师视频信息删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> udpateById(@RequestBody AuthCourseVideoDeleteBO authCourseVideoDeleteBO) {
        return biz.udpateById(authCourseVideoDeleteBO);
    }

}
