package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogLoginMapper {
    int countByExample(LogLoginExample example);

    int deleteByExample(LogLoginExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogLogin record);

    int insertSelective(LogLogin record);

    List<LogLogin> selectByExample(LogLoginExample example);

    LogLogin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogLogin record, @Param("example") LogLoginExample example);

    int updateByExample(@Param("record") LogLogin record, @Param("example") LogLoginExample example);

    int updateByPrimaryKeySelective(LogLogin record);

    int updateByPrimaryKey(LogLogin record);
}
