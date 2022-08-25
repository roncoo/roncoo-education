package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdv;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdvExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WebsiteAdvMapper {
    int countByExample(WebsiteAdvExample example);

    int deleteByExample(WebsiteAdvExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteAdv record);

    int insertSelective(WebsiteAdv record);

    List<WebsiteAdv> selectByExample(WebsiteAdvExample example);

    WebsiteAdv selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteAdv record, @Param("example") WebsiteAdvExample example);

    int updateByExample(@Param("record") WebsiteAdv record, @Param("example") WebsiteAdvExample example);

    int updateByPrimaryKeySelective(WebsiteAdv record);

    int updateByPrimaryKey(WebsiteAdv record);
}