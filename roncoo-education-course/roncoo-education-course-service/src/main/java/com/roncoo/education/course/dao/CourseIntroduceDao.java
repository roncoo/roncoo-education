package com.roncoo.education.course.dao;

import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduce;
import com.roncoo.education.common.core.base.Page;

public interface CourseIntroduceDao {
    int save(CourseIntroduce record);

    int deleteById(Long id);

    int updateById(CourseIntroduce record);

    CourseIntroduce getById(Long id);

    Page<CourseIntroduce> listForPage(int pageCurrent, int pageSize, CourseIntroduceExample example);
}
