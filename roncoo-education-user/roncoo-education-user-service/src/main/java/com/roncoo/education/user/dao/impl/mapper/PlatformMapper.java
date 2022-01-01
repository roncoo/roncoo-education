package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.dao.impl.mapper.entity.PlatformExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlatformMapper {
    int countByExample(PlatformExample example);

    int deleteByExample(PlatformExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Platform record);

    int insertSelective(Platform record);

    List<Platform> selectByExample(PlatformExample example);

    Platform selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Platform record, @Param("example") PlatformExample example);

    int updateByExample(@Param("record") Platform record, @Param("example") PlatformExample example);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKey(Platform record);
}
