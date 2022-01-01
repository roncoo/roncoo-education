package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.UserLogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.UserLogLoginExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserLogLoginMapper {
    int countByExample(UserLogLoginExample example);

    int deleteByExample(UserLogLoginExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLogLogin record);

    int insertSelective(UserLogLogin record);

    List<UserLogLogin> selectByExample(UserLogLoginExample example);

    UserLogLogin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLogLogin record, @Param("example") UserLogLoginExample example);

    int updateByExample(@Param("record") UserLogLogin record, @Param("example") UserLogLoginExample example);

    int updateByPrimaryKeySelective(UserLogLogin record);

    int updateByPrimaryKey(UserLogLogin record);
}
