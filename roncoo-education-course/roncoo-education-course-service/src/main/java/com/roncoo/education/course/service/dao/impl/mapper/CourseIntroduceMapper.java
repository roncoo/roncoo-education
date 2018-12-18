package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseIntroduce;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseIntroduceExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseIntroduceMapper {
    int countByExample(CourseIntroduceExample example);

    int deleteByExample(CourseIntroduceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseIntroduce record);

    int insertSelective(CourseIntroduce record);

    List<CourseIntroduce> selectByExampleWithBLOBs(CourseIntroduceExample example);

    List<CourseIntroduce> selectByExample(CourseIntroduceExample example);

    CourseIntroduce selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseIntroduce record, @Param("example") CourseIntroduceExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseIntroduce record, @Param("example") CourseIntroduceExample example);

    int updateByExample(@Param("record") CourseIntroduce record, @Param("example") CourseIntroduceExample example);

    int updateByPrimaryKeySelective(CourseIntroduce record);

    int updateByPrimaryKeyWithBLOBs(CourseIntroduce record);

    int updateByPrimaryKey(CourseIntroduce record);
}