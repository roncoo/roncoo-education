package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticle;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteArticleExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WebsiteArticleMapper {
    int countByExample(WebsiteArticleExample example);

    int deleteByExample(WebsiteArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteArticle record);

    int insertSelective(WebsiteArticle record);

    List<WebsiteArticle> selectByExampleWithBLOBs(WebsiteArticleExample example);

    List<WebsiteArticle> selectByExample(WebsiteArticleExample example);

    WebsiteArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteArticle record, @Param("example") WebsiteArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") WebsiteArticle record, @Param("example") WebsiteArticleExample example);

    int updateByExample(@Param("record") WebsiteArticle record, @Param("example") WebsiteArticleExample example);

    int updateByPrimaryKeySelective(WebsiteArticle record);

    int updateByPrimaryKeyWithBLOBs(WebsiteArticle record);

    int updateByPrimaryKey(WebsiteArticle record);
}