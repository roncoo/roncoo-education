package com.roncoo.education.course.service.api.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.ZoneCourseDao;
import com.roncoo.education.course.dao.ZoneDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample.Criteria;
import com.roncoo.education.course.service.api.bo.ZoneBO;
import com.roncoo.education.course.service.api.dto.ZoneCourseDTO;
import com.roncoo.education.course.service.api.dto.ZoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApiZoneBiz {

    @Autowired
    private ZoneDao zoneDao;
    @Autowired
    private ZoneCourseDao zoneCourseDao;
    @Autowired
    private CourseDao courseDao;

    /**
     * 专区课程分页列表接口
     *
     * @param zoneBO
     * @author wuyun
     */
    public Result<Page<ZoneDTO>> listForPage(ZoneBO zoneBO) {
        if (StringUtils.isEmpty(zoneBO.getZoneLocation())) {
            return Result.error("zoneLocation不能为空");
        }
        ZoneExample example = new ZoneExample();
        Criteria c = example.createCriteria();
        c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        if (!StringUtils.isEmpty(zoneBO.getId())) {
            c.andIdEqualTo(zoneBO.getId());
        }
        if (!StringUtils.isEmpty(zoneBO.getZoneLocation())) {
            c.andZoneLocationEqualTo(zoneBO.getZoneLocation());
        }
        example.setOrderByClause("sort desc, id desc");
        Page<Zone> zonePage = zoneDao.listForPage(zoneBO.getPageCurrent(), zoneBO.getPageSize(), example);
        if (StringUtils.isEmpty(zonePage)) {
            return Result.error("找不到信息");
        }
        Page<ZoneDTO> page = PageUtil.transform(zonePage, ZoneDTO.class);
        for (ZoneDTO zone : page.getList()) {
            List<ZoneCourse> zoneCourseList = zoneCourseDao.listByZoneIdAndStatusId(zone.getId(), StatusIdEnum.YES.getCode());
            List<ZoneCourseDTO> zoneCourseListDTO = new ArrayList<>();
            for (ZoneCourse zoneCourse : zoneCourseList) {
                Course course = courseDao.getById(zoneCourse.getCourseId());
                zoneCourseListDTO.add(BeanUtil.copyProperties(course, ZoneCourseDTO.class));
            }
            zone.setZoneCourseList(zoneCourseListDTO);
        }
        return Result.success(page);
    }

}
