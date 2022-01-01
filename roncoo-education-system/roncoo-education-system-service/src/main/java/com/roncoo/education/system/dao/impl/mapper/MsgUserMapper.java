package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MsgUserMapper {
    int countByExample(MsgUserExample example);

    int deleteByExample(MsgUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MsgUser record);

    int insertSelective(MsgUser record);

    List<MsgUser> selectByExample(MsgUserExample example);

    MsgUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MsgUser record, @Param("example") MsgUserExample example);

    int updateByExample(@Param("record") MsgUser record, @Param("example") MsgUserExample example);

    int updateByPrimaryKeySelective(MsgUser record);

    int updateByPrimaryKey(MsgUser record);
}
