package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseComment;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCourseCommentMapper {
    int countByExample(UserCourseCommentExample example);

    int deleteByExample(UserCourseCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCourseComment record);

    int insertSelective(UserCourseComment record);

    List<UserCourseComment> selectByExample(UserCourseCommentExample example);

    UserCourseComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCourseComment record, @Param("example") UserCourseCommentExample example);

    int updateByExample(@Param("record") UserCourseComment record, @Param("example") UserCourseCommentExample example);

    int updateByPrimaryKeySelective(UserCourseComment record);

    int updateByPrimaryKey(UserCourseComment record);
}
