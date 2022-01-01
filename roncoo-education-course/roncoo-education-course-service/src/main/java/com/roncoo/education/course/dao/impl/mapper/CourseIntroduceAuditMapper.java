package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceAuditExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseIntroduceAuditMapper {
    int countByExample(CourseIntroduceAuditExample example);

    int deleteByExample(CourseIntroduceAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseIntroduceAudit record);

    int insertSelective(CourseIntroduceAudit record);

    List<CourseIntroduceAudit> selectByExampleWithBLOBs(CourseIntroduceAuditExample example);

    List<CourseIntroduceAudit> selectByExample(CourseIntroduceAuditExample example);

    CourseIntroduceAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseIntroduceAudit record, @Param("example") CourseIntroduceAuditExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseIntroduceAudit record, @Param("example") CourseIntroduceAuditExample example);

    int updateByExample(@Param("record") CourseIntroduceAudit record, @Param("example") CourseIntroduceAuditExample example);

    int updateByPrimaryKeySelective(CourseIntroduceAudit record);

    int updateByPrimaryKeyWithBLOBs(CourseIntroduceAudit record);

    int updateByPrimaryKey(CourseIntroduceAudit record);
}
