package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseVideoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseVideoMapper {
    int countByExample(CourseVideoExample example);

    int deleteByExample(CourseVideoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseVideo record);

    int insertSelective(CourseVideo record);

    List<CourseVideo> selectByExample(CourseVideoExample example);

    CourseVideo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseVideo record, @Param("example") CourseVideoExample example);

    int updateByExample(@Param("record") CourseVideo record, @Param("example") CourseVideoExample example);

    int updateByPrimaryKeySelective(CourseVideo record);

    int updateByPrimaryKey(CourseVideo record);
}
