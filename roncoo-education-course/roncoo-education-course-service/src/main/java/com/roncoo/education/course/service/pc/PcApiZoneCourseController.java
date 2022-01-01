package com.roncoo.education.course.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.pc.biz.PcApiZoneCourseBiz;
import com.roncoo.education.course.service.pc.req.*;
import com.roncoo.education.course.service.pc.resq.ZoneCoursePageRESQ;
import com.roncoo.education.course.service.pc.resq.ZoneCourseViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专区课程关联表
 */
@RestController
@RequestMapping(value = "/course/pc/zone/course")
public class PcApiZoneCourseController extends BaseController {

    @Autowired
    private PcApiZoneCourseBiz biz;

    /**
     * 分页列出专区信息
     *
     * @param zoneCoursePageREQ
     * @return
     */
    @ApiOperation(value = "分页列出专区课程信息", notes = "分页列出专区课程信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<ZoneCoursePageRESQ>> listForPage(@RequestBody ZoneCoursePageREQ zoneCoursePageREQ) {
        return biz.listForPage(zoneCoursePageREQ);
    }

    /**
     * 添加专区课程信息
     *
     * @param zoneCourseSaveREQ
     * @return
     */
    @ApiOperation(value = "添加专区课程信息", notes = "添加专区课程信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody ZoneCourseSaveREQ zoneCourseSaveREQ) {
        return biz.save(zoneCourseSaveREQ);
    }

    /**
     * 删除专区课程信息
     *
     * @param zoneCourseDeleteREQ
     * @return
     */
    @ApiOperation(value = "删除专区课程信息", notes = "删除专区课程信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody ZoneCourseDeleteREQ zoneCourseDeleteREQ) {
        return biz.delete(zoneCourseDeleteREQ);
    }

    /**
     * 更新专区课程信息
     *
     * @param zoneCourseUpdateREQ
     * @return
     */
    @ApiOperation(value = "更新专区课程信息", notes = "更新专区课程信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody ZoneCourseUpdateREQ zoneCourseUpdateREQ) {
        return biz.update(zoneCourseUpdateREQ);
    }

    /**
     * 查看专区课程信息
     *
     * @param zoneCourseViewREQ
     * @return
     */
    @ApiOperation(value = "查看专区课程信息", notes = "查看专区课程信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<ZoneCourseViewRESQ> view(@RequestBody ZoneCourseViewREQ zoneCourseViewREQ) {
        return biz.view(zoneCourseViewREQ);
    }

}
