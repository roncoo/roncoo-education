package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.CourseUserStudy;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseUserStudyExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseUserStudyMapper {
    int countByExample(CourseUserStudyExample example);

    int deleteByExample(CourseUserStudyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseUserStudy record);

    int insertSelective(CourseUserStudy record);

    List<CourseUserStudy> selectByExample(CourseUserStudyExample example);

    CourseUserStudy selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseUserStudy record, @Param("example") CourseUserStudyExample example);

    int updateByExample(@Param("record") CourseUserStudy record, @Param("example") CourseUserStudyExample example);

    int updateByPrimaryKeySelective(CourseUserStudy record);

    int updateByPrimaryKey(CourseUserStudy record);
}
