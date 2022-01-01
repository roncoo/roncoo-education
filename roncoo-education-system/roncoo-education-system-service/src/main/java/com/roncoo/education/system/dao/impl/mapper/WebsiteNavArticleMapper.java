package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavArticleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebsiteNavArticleMapper {
    int countByExample(WebsiteNavArticleExample example);

    int deleteByExample(WebsiteNavArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteNavArticle record);

    int insertSelective(WebsiteNavArticle record);

    List<WebsiteNavArticle> selectByExampleWithBLOBs(WebsiteNavArticleExample example);

    List<WebsiteNavArticle> selectByExample(WebsiteNavArticleExample example);

    WebsiteNavArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteNavArticle record, @Param("example") WebsiteNavArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") WebsiteNavArticle record, @Param("example") WebsiteNavArticleExample example);

    int updateByExample(@Param("record") WebsiteNavArticle record, @Param("example") WebsiteNavArticleExample example);

    int updateByPrimaryKeySelective(WebsiteNavArticle record);

    int updateByPrimaryKeyWithBLOBs(WebsiteNavArticle record);

    int updateByPrimaryKey(WebsiteNavArticle record);
}
