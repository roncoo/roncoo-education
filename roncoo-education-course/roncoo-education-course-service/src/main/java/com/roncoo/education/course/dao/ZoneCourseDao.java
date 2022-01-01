package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample;

import java.util.List;

public interface ZoneCourseDao {
    int save(ZoneCourse record);

    int deleteById(Long id);

    int updateById(ZoneCourse record);

    ZoneCourse getById(Long id);

    Page<ZoneCourse> listForPage(int pageCurrent, int pageSize, ZoneCourseExample example);

    /**
     * 根据专区编号获取可用的专区课程信息集合
     *
     * @param zoneId
     * @param statusId
     * @return
     * @author wuyun
     */
    List<ZoneCourse> listByZoneIdAndStatusId(Long zoneId, Integer statusId);

    /**
     * 根据专区编号,获取可以专区课程信息
     *
     * @param zoneId
     * @return
     */
    List<ZoneCourse> listByZoneId(Long zoneId);

    ZoneCourse getZoneIdAndCourseId(Long courseId, Long zoneId);

}
