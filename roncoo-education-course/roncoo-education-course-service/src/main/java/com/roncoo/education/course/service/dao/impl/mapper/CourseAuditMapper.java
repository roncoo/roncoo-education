package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseAuditMapper {
    int countByExample(CourseAuditExample example);

    int deleteByExample(CourseAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseAudit record);

    int insertSelective(CourseAudit record);

    List<CourseAudit> selectByExample(CourseAuditExample example);

    CourseAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseAudit record, @Param("example") CourseAuditExample example);

    int updateByExample(@Param("record") CourseAudit record, @Param("example") CourseAuditExample example);

    int updateByPrimaryKeySelective(CourseAudit record);

    int updateByPrimaryKey(CourseAudit record);
}