package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollect;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollectExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCourseCollectMapper {
    int countByExample(UserCourseCollectExample example);

    int deleteByExample(UserCourseCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCourseCollect record);

    int insertSelective(UserCourseCollect record);

    List<UserCourseCollect> selectByExample(UserCourseCollectExample example);

    UserCourseCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCourseCollect record, @Param("example") UserCourseCollectExample example);

    int updateByExample(@Param("record") UserCourseCollect record, @Param("example") UserCourseCollectExample example);

    int updateByPrimaryKeySelective(UserCourseCollect record);

    int updateByPrimaryKey(UserCourseCollect record);
}
