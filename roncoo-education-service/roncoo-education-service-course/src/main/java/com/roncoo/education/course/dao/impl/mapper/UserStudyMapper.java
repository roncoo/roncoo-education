package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudyExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserStudyMapper {
    int countByExample(UserStudyExample example);

    int deleteByExample(UserStudyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserStudy record);

    int insertSelective(UserStudy record);

    List<UserStudy> selectByExample(UserStudyExample example);

    UserStudy selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserStudy record, @Param("example") UserStudyExample example);

    int updateByExample(@Param("record") UserStudy record, @Param("example") UserStudyExample example);

    int updateByPrimaryKeySelective(UserStudy record);

    int updateByPrimaryKey(UserStudy record);
}
