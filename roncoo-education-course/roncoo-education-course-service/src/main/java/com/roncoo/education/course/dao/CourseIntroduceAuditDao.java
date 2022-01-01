package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceAuditExample;

public interface CourseIntroduceAuditDao {
    int save(CourseIntroduceAudit record);

    int deleteById(Long id);

    int updateById(CourseIntroduceAudit record);

    CourseIntroduceAudit getById(Long id);

    Page<CourseIntroduceAudit> listForPage(int pageCurrent, int pageSize, CourseIntroduceAuditExample example);
}
