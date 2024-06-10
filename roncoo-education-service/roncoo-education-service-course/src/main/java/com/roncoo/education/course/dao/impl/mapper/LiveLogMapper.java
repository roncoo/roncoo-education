package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.LiveLog;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LiveLogMapper {
    int countByExample(LiveLogExample example);

    int deleteByExample(LiveLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LiveLog record);

    int insertSelective(LiveLog record);

    List<LiveLog> selectByExample(LiveLogExample example);

    LiveLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LiveLog record, @Param("example") LiveLogExample example);

    int updateByExample(@Param("record") LiveLog record, @Param("example") LiveLogExample example);

    int updateByPrimaryKeySelective(LiveLog record);

    int updateByPrimaryKey(LiveLog record);
}