package com.roncoo.education.system.dao.impl.mapper;

import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuRoleMapper {
    int countByExample(SysMenuRoleExample example);

    int deleteByExample(SysMenuRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMenuRole record);

    int insertSelective(SysMenuRole record);

    List<SysMenuRole> selectByExample(SysMenuRoleExample example);

    SysMenuRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    int updateByExample(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    int updateByPrimaryKeySelective(SysMenuRole record);

    int updateByPrimaryKey(SysMenuRole record);
}
