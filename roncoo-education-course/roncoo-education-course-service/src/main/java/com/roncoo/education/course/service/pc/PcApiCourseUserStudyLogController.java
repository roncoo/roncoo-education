package com.roncoo.education.course.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.pc.biz.PcApiCourseUserStudyLogBiz;
import com.roncoo.education.course.service.pc.req.CourseUserStudyLogPageREQ;
import com.roncoo.education.course.service.pc.req.CourseUserStudyLogPlayREQ;
import com.roncoo.education.course.service.pc.resq.CourseUserStudyLogPageRESQ;
import com.roncoo.education.course.service.pc.resq.CourseUserStudyLogPlayRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程用户学习日志
 */
@RestController
@RequestMapping(value = "/course/pc/course/user/study/log")
public class PcApiCourseUserStudyLogController extends BaseController {

    @Autowired
    private PcApiCourseUserStudyLogBiz biz;

    /**
     * 分页列出用户学习日志
     *
     * @param courseUserStudyLogREQ
     * @return
     */
    @ApiOperation(value = "用户学习日志分页列表接口", notes = "用户学习日志分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<CourseUserStudyLogPageRESQ>> list(@RequestBody CourseUserStudyLogPageREQ courseUserStudyLogREQ) {
        return biz.list(courseUserStudyLogREQ);
    }

    /**
     * 课时播放量排行榜
     *
     * @return
     */
    @ApiOperation(value = "课时播放量排行榜接口", notes = "课时播放量排行榜接口")
    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public Result<Page<CourseUserStudyLogPlayRESQ>> play(@RequestBody CourseUserStudyLogPlayREQ courseUserStudyLogPlayREQ) {
        return biz.play(courseUserStudyLogPlayREQ);
    }

}
