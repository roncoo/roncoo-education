package com.roncoo.education.system.service.dao.impl.mapper;

import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteLink;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WebsiteLinkMapper {
    int countByExample(WebsiteLinkExample example);

    int deleteByExample(WebsiteLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteLink record);

    int insertSelective(WebsiteLink record);

    List<WebsiteLink> selectByExample(WebsiteLinkExample example);

    WebsiteLink selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteLink record, @Param("example") WebsiteLinkExample example);

    int updateByExample(@Param("record") WebsiteLink record, @Param("example") WebsiteLinkExample example);

    int updateByPrimaryKeySelective(WebsiteLink record);

    int updateByPrimaryKey(WebsiteLink record);
}