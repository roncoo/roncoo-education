package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.Adv;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AdvExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdvMapper {
    int countByExample(AdvExample example);

    int deleteByExample(AdvExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Adv record);

    int insertSelective(Adv record);

    List<Adv> selectByExample(AdvExample example);

    Adv selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Adv record, @Param("example") AdvExample example);

    int updateByExample(@Param("record") Adv record, @Param("example") AdvExample example);

    int updateByPrimaryKeySelective(Adv record);

    int updateByPrimaryKey(Adv record);
}