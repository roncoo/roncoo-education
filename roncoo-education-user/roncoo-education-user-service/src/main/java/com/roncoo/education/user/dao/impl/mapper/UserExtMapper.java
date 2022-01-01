package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExtExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserExtMapper {
    int countByExample(UserExtExample example);

    int deleteByExample(UserExtExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserExt record);

    int insertSelective(UserExt record);

    List<UserExt> selectByExample(UserExtExample example);

    UserExt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserExt record, @Param("example") UserExtExample example);

    int updateByExample(@Param("record") UserExt record, @Param("example") UserExtExample example);

    int updateByPrimaryKeySelective(UserExt record);

    int updateByPrimaryKey(UserExt record);
}
