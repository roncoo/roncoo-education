package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogModified;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogModifiedExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLogModifiedMapper {
    int countByExample(UserLogModifiedExample example);

    int deleteByExample(UserLogModifiedExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLogModified record);

    int insertSelective(UserLogModified record);

    List<UserLogModified> selectByExample(UserLogModifiedExample example);

    UserLogModified selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLogModified record, @Param("example") UserLogModifiedExample example);

    int updateByExample(@Param("record") UserLogModified record, @Param("example") UserLogModifiedExample example);

    int updateByPrimaryKeySelective(UserLogModified record);

    int updateByPrimaryKey(UserLogModified record);
}