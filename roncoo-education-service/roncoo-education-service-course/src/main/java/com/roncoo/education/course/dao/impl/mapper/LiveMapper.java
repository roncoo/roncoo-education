package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.Live;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LiveMapper {
    int countByExample(LiveExample example);

    int deleteByExample(LiveExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Live record);

    int insertSelective(Live record);

    List<Live> selectByExample(LiveExample example);

    Live selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Live record, @Param("example") LiveExample example);

    int updateByExample(@Param("record") Live record, @Param("example") LiveExample example);

    int updateByPrimaryKeySelective(Live record);

    int updateByPrimaryKey(Live record);
}