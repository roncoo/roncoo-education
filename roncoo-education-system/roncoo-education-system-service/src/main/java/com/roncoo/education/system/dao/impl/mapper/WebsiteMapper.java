package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.Website;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebsiteMapper {
    int countByExample(WebsiteExample example);

    int deleteByExample(WebsiteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Website record);

    int insertSelective(Website record);

    List<Website> selectByExample(WebsiteExample example);

    Website selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Website record, @Param("example") WebsiteExample example);

    int updateByExample(@Param("record") Website record, @Param("example") WebsiteExample example);

    int updateByPrimaryKeySelective(Website record);

    int updateByPrimaryKey(Website record);
}
