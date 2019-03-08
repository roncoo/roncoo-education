package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.SendSmsLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SendSmsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SendSmsLogMapper {
    int countByExample(SendSmsLogExample example);

    int deleteByExample(SendSmsLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SendSmsLog record);

    int insertSelective(SendSmsLog record);

    List<SendSmsLog> selectByExample(SendSmsLogExample example);

    SendSmsLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SendSmsLog record, @Param("example") SendSmsLogExample example);

    int updateByExample(@Param("record") SendSmsLog record, @Param("example") SendSmsLogExample example);

    int updateByPrimaryKeySelective(SendSmsLog record);

    int updateByPrimaryKey(SendSmsLog record);
}