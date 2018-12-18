package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseIntroduceAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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