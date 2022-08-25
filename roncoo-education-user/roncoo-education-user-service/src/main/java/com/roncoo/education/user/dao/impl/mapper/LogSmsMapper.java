package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.LogSms;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSmsExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogSmsMapper {
    int countByExample(LogSmsExample example);

    int deleteByExample(LogSmsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogSms record);

    int insertSelective(LogSms record);

    List<LogSms> selectByExample(LogSmsExample example);

    LogSms selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogSms record, @Param("example") LogSmsExample example);

    int updateByExample(@Param("record") LogSms record, @Param("example") LogSmsExample example);

    int updateByPrimaryKeySelective(LogSms record);

    int updateByPrimaryKey(LogSms record);
}