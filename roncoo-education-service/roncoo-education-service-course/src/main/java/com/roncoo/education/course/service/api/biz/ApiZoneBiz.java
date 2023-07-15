package com.roncoo.education.course.service.api.biz;

import cn.hutool.core.collection.CollUtil;
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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fengyw
 */
@Component
@CacheConfig(cacheNames = {"course"})
public class ApiZoneBiz {

    @Autowired
    private ZoneDao zoneDao;
    @Autowired
    private ZoneCourseDao zoneCourseDao;
    @Autowired
    private CourseDao courseDao;

    @Cacheable
    public Result<List<ApiZoneResp>> list() {
        // 获取所有可用专区
        ZoneExample example = new ZoneExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        example.setOrderByClause("sort asc, id desc");
        List<Zone> zoneList = zoneDao.listByExample(example);
        List<ApiZoneResp> result = BeanUtil.copyProperties(zoneList, ApiZoneResp.class);
        if (CollUtil.isNotEmpty(result)) {
            for (ApiZoneResp resp : result) {
                List<Long> courseIds = zoneCourse(resp.getId());
                List<Course> courseList = courseDao.listByIds(courseIds);
                resp.setCourseList(BeanUtil.copyProperties(courseList, ApiZoneCourseResp.class));
            }
        }
        return Result.success(result);
    }

    private List<Long> zoneCourse(Long zoneId) {
        ZoneCourseExample example = new ZoneCourseExample();
        example.createCriteria().andZoneIdEqualTo(zoneId);
        example.setOrderByClause("sort asc, id desc");
        List<ZoneCourse> result = zoneCourseDao.listByExample(example);
        return result.stream().map(ZoneCourse::getCourseId).collect(Collectors.toList());
    }
}
