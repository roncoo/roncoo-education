package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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