package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseChapterAuditMapper {
    int countByExample(CourseChapterAuditExample example);

    int deleteByExample(CourseChapterAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseChapterAudit record);

    int insertSelective(CourseChapterAudit record);

    List<CourseChapterAudit> selectByExample(CourseChapterAuditExample example);

    CourseChapterAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseChapterAudit record, @Param("example") CourseChapterAuditExample example);

    int updateByExample(@Param("record") CourseChapterAudit record, @Param("example") CourseChapterAuditExample example);

    int updateByPrimaryKeySelective(CourseChapterAudit record);

    int updateByPrimaryKey(CourseChapterAudit record);
}