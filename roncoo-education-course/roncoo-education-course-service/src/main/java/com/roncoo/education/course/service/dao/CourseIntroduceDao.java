package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseIntroduce;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseIntroduceExample;
import com.roncoo.education.util.base.Page;

public interface CourseIntroduceDao {
    int save(CourseIntroduce record);

    int deleteById(Long id);

    int updateById(CourseIntroduce record);

    CourseIntroduce getById(Long id);

    Page<CourseIntroduce> listForPage(int pageCurrent, int pageSize, CourseIntroduceExample example);
}