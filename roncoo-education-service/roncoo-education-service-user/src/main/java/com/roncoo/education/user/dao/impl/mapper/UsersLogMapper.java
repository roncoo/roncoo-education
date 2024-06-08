package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.UsersLog;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersLogMapper {
    int countByExample(UsersLogExample example);

    int deleteByExample(UsersLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UsersLog record);

    int insertSelective(UsersLog record);

    List<UsersLog> selectByExample(UsersLogExample example);

    UsersLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UsersLog record, @Param("example") UsersLogExample example);

    int updateByExample(@Param("record") UsersLog record, @Param("example") UsersLogExample example);

    int updateByPrimaryKeySelective(UsersLog record);

    int updateByPrimaryKey(UsersLog record);
}