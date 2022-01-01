package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.NavBar;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBarExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NavBarMapper {
    int countByExample(NavBarExample example);

    int deleteByExample(NavBarExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NavBar record);

    int insertSelective(NavBar record);

    List<NavBar> selectByExample(NavBarExample example);

    NavBar selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NavBar record, @Param("example") NavBarExample example);

    int updateByExample(@Param("record") NavBar record, @Param("example") NavBarExample example);

    int updateByPrimaryKeySelective(NavBar record);

    int updateByPrimaryKey(NavBar record);
}
