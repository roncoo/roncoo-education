package com.roncoo.education.course.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.pc.biz.PcApiCourseBiz;
import com.roncoo.education.course.service.pc.req.CourseGetREQ;
import com.roncoo.education.course.service.pc.req.CoursePageREQ;
import com.roncoo.education.course.service.pc.req.CourseUpdateREQ;
import com.roncoo.education.course.service.pc.req.CourseViewREQ;
import com.roncoo.education.course.service.pc.resq.CourseGetRESQ;
import com.roncoo.education.course.service.pc.resq.CoursePageRESQ;
import com.roncoo.education.course.service.pc.resq.CourseViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程信息
 */
@RestController
@RequestMapping(value = "/course/pc/course")
public class PcApiCourseController extends BaseController {

    @Autowired
    private PcApiCourseBiz biz;

    /**
     * 课程分页列出接口
     *
     * @param coursePageREQ
     * @return
     */
    @ApiOperation(value = "课程分页列出接口", notes = "课程分页列出接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<CoursePageRESQ>> list(@RequestBody CoursePageREQ coursePageREQ) {
        return biz.list(coursePageREQ);
    }

    /**
     * 课程更新接口
     *
     * @param courseUpdateREQ
     * @return
     */
    @ApiOperation(value = "课程更新接口", notes = "课程更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody CourseUpdateREQ courseUpdateREQ) {
        return biz.update(courseUpdateREQ);
    }

    /**
     * 课程查看接口(课程修改使用)
     *
     * @param courseGetREQ
     * @return
     */
    @ApiOperation(value = "课程查看接口(课程修改使用)", notes = "课程查看接口(课程修改使用)")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Result<CourseGetRESQ> get(@RequestBody CourseGetREQ courseGetREQ) {
        return biz.get(courseGetREQ);
    }

    /**
     * 课程查看接口
     *
     * @param courseUpdateREQ
     * @return
     */
    @ApiOperation(value = "课程查看详情接口", notes = "课程查看详情接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<CourseViewRESQ> view(@RequestBody CourseViewREQ courseViewREQ) {
        return biz.view(courseViewREQ);
    }

}
