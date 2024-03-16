package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersAccountMapper {
    int countByExample(UsersAccountExample example);

    int deleteByExample(UsersAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UsersAccount record);

    int insertSelective(UsersAccount record);

    List<UsersAccount> selectByExample(UsersAccountExample example);

    UsersAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UsersAccount record, @Param("example") UsersAccountExample example);

    int updateByExample(@Param("record") UsersAccount record, @Param("example") UsersAccountExample example);

    int updateByPrimaryKeySelective(UsersAccount record);

    int updateByPrimaryKey(UsersAccount record);
}