package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseChapterPeriodMapper {
    int countByExample(CourseChapterPeriodExample example);

    int deleteByExample(CourseChapterPeriodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseChapterPeriod record);

    int insertSelective(CourseChapterPeriod record);

    List<CourseChapterPeriod> selectByExample(CourseChapterPeriodExample example);

    CourseChapterPeriod selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseChapterPeriod record, @Param("example") CourseChapterPeriodExample example);

    int updateByExample(@Param("record") CourseChapterPeriod record, @Param("example") CourseChapterPeriodExample example);

    int updateByPrimaryKeySelective(CourseChapterPeriod record);

    int updateByPrimaryKey(CourseChapterPeriod record);
}