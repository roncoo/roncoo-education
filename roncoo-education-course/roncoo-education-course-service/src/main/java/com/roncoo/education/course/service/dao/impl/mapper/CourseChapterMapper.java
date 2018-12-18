package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseChapterMapper {
    int countByExample(CourseChapterExample example);

    int deleteByExample(CourseChapterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseChapter record);

    int insertSelective(CourseChapter record);

    List<CourseChapter> selectByExample(CourseChapterExample example);

    CourseChapter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseChapter record, @Param("example") CourseChapterExample example);

    int updateByExample(@Param("record") CourseChapter record, @Param("example") CourseChapterExample example);

    int updateByPrimaryKeySelective(CourseChapter record);

    int updateByPrimaryKey(CourseChapter record);
}