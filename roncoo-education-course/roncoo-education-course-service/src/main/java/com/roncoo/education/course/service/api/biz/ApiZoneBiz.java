package com.roncoo.education.course.service.api.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.ZoneCourseDao;
import com.roncoo.education.course.dao.ZoneDao;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.api.resp.ApiZoneCourseResp;
import com.roncoo.education.course.service.api.resp.ApiZoneResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fengyw
 */
@Component
public class ApiZoneBiz {

    @Autowired
    private ZoneDao zoneDao;
    @Autowired
    private ZoneCourseDao zoneCourseDao;
    @Autowired
    private CourseDao courseDao;

    public Result<List<ApiZoneResp>> list() {
        // 获取所有可用专区
        ZoneExample example = new ZoneExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        List<Zone> zoneList = zoneDao.listByExample(example);
        List<ApiZoneResp> result = new ArrayList<>();
        for (Zone zone : zoneList) {
            // 获取专区下的课程
            List<Long> courseIds = zoneCourse(zone);
            // 获取对应的课程信息
            List<Course> courseList = courseDao.listByIds(courseIds);

            ApiZoneResp resp = BeanUtil.copyProperties(zone, ApiZoneResp.class);
            resp.setCourseList(BeanUtil.copyProperties(courseList, ApiZoneCourseResp.class));
            result.add(resp);
        }
        return Result.success(result);
    }

    private List<Long> zoneCourse(Zone zone) {
        ZoneCourseExample example = new ZoneCourseExample();
        example.createCriteria().andZoneIdEqualTo(zone.getId());
        List<ZoneCourse> result = zoneCourseDao.listByExample(example);
        return result.stream().map(ZoneCourse::getCourseId).collect(Collectors.toList());
    }
}
