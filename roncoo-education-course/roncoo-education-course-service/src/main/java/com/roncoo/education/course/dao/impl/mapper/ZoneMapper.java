package com.roncoo.education.course.dao.impl.mapper;

import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZoneMapper {
    int countByExample(ZoneExample example);

    int deleteByExample(ZoneExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Zone record);

    int insertSelective(Zone record);

    List<Zone> selectByExample(ZoneExample example);

    Zone selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Zone record, @Param("example") ZoneExample example);

    int updateByExample(@Param("record") Zone record, @Param("example") ZoneExample example);

    int updateByPrimaryKeySelective(Zone record);

    int updateByPrimaryKey(Zone record);
}
