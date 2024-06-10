package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.LiveChannel;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveChannelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LiveChannelMapper {
    int countByExample(LiveChannelExample example);

    int deleteByExample(LiveChannelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LiveChannel record);

    int insertSelective(LiveChannel record);

    List<LiveChannel> selectByExample(LiveChannelExample example);

    LiveChannel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LiveChannel record, @Param("example") LiveChannelExample example);

    int updateByExample(@Param("record") LiveChannel record, @Param("example") LiveChannelExample example);

    int updateByPrimaryKeySelective(LiveChannel record);

    int updateByPrimaryKey(LiveChannel record);
}