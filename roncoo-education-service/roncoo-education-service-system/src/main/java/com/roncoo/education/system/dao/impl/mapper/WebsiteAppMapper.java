package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteApp;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAppExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WebsiteAppMapper {
    int countByExample(WebsiteAppExample example);

    int deleteByExample(WebsiteAppExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteApp record);

    int insertSelective(WebsiteApp record);

    List<WebsiteApp> selectByExample(WebsiteAppExample example);

    WebsiteApp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteApp record, @Param("example") WebsiteAppExample example);

    int updateByExample(@Param("record") WebsiteApp record, @Param("example") WebsiteAppExample example);

    int updateByPrimaryKeySelective(WebsiteApp record);

    int updateByPrimaryKey(WebsiteApp record);
}