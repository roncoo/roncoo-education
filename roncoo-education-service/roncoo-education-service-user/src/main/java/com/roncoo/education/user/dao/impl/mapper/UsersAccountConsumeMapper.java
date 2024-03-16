package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsume;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsumeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersAccountConsumeMapper {
    int countByExample(UsersAccountConsumeExample example);

    int deleteByExample(UsersAccountConsumeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UsersAccountConsume record);

    int insertSelective(UsersAccountConsume record);

    List<UsersAccountConsume> selectByExample(UsersAccountConsumeExample example);

    UsersAccountConsume selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UsersAccountConsume record, @Param("example") UsersAccountConsumeExample example);

    int updateByExample(@Param("record") UsersAccountConsume record, @Param("example") UsersAccountConsumeExample example);

    int updateByPrimaryKeySelective(UsersAccountConsume record);

    int updateByPrimaryKey(UsersAccountConsume record);
}