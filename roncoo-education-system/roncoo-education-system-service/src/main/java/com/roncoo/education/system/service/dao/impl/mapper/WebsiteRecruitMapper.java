package com.roncoo.education.system.service.dao.impl.mapper;

import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruit;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruitExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WebsiteRecruitMapper {
    int countByExample(WebsiteRecruitExample example);

    int deleteByExample(WebsiteRecruitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteRecruit record);

    int insertSelective(WebsiteRecruit record);

    List<WebsiteRecruit> selectByExample(WebsiteRecruitExample example);

    WebsiteRecruit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteRecruit record, @Param("example") WebsiteRecruitExample example);

    int updateByExample(@Param("record") WebsiteRecruit record, @Param("example") WebsiteRecruitExample example);

    int updateByPrimaryKeySelective(WebsiteRecruit record);

    int updateByPrimaryKey(WebsiteRecruit record);
}