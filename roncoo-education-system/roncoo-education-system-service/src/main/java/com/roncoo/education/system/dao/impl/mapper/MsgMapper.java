package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.Msg;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MsgMapper {
    int countByExample(MsgExample example);

    int deleteByExample(MsgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Msg record);

    int insertSelective(Msg record);

    List<Msg> selectByExampleWithBLOBs(MsgExample example);

    List<Msg> selectByExample(MsgExample example);

    Msg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Msg record, @Param("example") MsgExample example);

    int updateByExampleWithBLOBs(@Param("record") Msg record, @Param("example") MsgExample example);

    int updateByExample(@Param("record") Msg record, @Param("example") MsgExample example);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKeyWithBLOBs(Msg record);

    int updateByPrimaryKey(Msg record);
}
