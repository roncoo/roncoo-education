package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarousel;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarouselExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebsiteCarouselMapper {
    int countByExample(WebsiteCarouselExample example);

    int deleteByExample(WebsiteCarouselExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteCarousel record);

    int insertSelective(WebsiteCarousel record);

    List<WebsiteCarousel> selectByExample(WebsiteCarouselExample example);

    WebsiteCarousel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteCarousel record, @Param("example") WebsiteCarouselExample example);

    int updateByExample(@Param("record") WebsiteCarousel record, @Param("example") WebsiteCarouselExample example);

    int updateByPrimaryKeySelective(WebsiteCarousel record);

    int updateByPrimaryKey(WebsiteCarousel record);
}
