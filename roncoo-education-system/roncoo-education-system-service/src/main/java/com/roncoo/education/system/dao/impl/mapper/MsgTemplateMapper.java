package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplateExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MsgTemplateMapper {
    int countByExample(MsgTemplateExample example);

    int deleteByExample(MsgTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MsgTemplate record);

    int insertSelective(MsgTemplate record);

    List<MsgTemplate> selectByExample(MsgTemplateExample example);

    MsgTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MsgTemplate record, @Param("example") MsgTemplateExample example);

    int updateByExample(@Param("record") MsgTemplate record, @Param("example") MsgTemplateExample example);

    int updateByPrimaryKeySelective(MsgTemplate record);

    int updateByPrimaryKey(MsgTemplate record);
}
