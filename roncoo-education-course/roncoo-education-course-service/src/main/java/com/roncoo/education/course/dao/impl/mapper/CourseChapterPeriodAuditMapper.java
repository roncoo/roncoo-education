package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAuditExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseChapterPeriodAuditMapper {
    int countByExample(CourseChapterPeriodAuditExample example);

    int deleteByExample(CourseChapterPeriodAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseChapterPeriodAudit record);

    int insertSelective(CourseChapterPeriodAudit record);

    List<CourseChapterPeriodAudit> selectByExample(CourseChapterPeriodAuditExample example);

    CourseChapterPeriodAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseChapterPeriodAudit record, @Param("example") CourseChapterPeriodAuditExample example);

    int updateByExample(@Param("record") CourseChapterPeriodAudit record, @Param("example") CourseChapterPeriodAuditExample example);

    int updateByPrimaryKeySelective(CourseChapterPeriodAudit record);

    int updateByPrimaryKey(CourseChapterPeriodAudit record);
}
