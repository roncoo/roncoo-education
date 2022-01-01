package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebsiteNavMapper {
    int countByExample(WebsiteNavExample example);

    int deleteByExample(WebsiteNavExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteNav record);

    int insertSelective(WebsiteNav record);

    List<WebsiteNav> selectByExample(WebsiteNavExample example);

    WebsiteNav selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteNav record, @Param("example") WebsiteNavExample example);

    int updateByExample(@Param("record") WebsiteNav record, @Param("example") WebsiteNavExample example);

    int updateByPrimaryKeySelective(WebsiteNav record);

    int updateByPrimaryKey(WebsiteNav record);
}
