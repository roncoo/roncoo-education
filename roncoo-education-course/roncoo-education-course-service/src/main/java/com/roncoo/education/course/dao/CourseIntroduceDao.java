package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduce;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceExample;

public interface CourseIntroduceDao {
    int save(CourseIntroduce record);

    int deleteById(Long id);

    int updateById(CourseIntroduce record);

    CourseIntroduce getById(Long id);

    Page<CourseIntroduce> listForPage(int pageCurrent, int pageSize, CourseIntroduceExample example);
}
