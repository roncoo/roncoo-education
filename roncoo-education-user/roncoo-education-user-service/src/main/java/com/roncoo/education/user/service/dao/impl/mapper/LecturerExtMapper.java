package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LecturerExtMapper {
    int countByExample(LecturerExtExample example);

    int deleteByExample(LecturerExtExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LecturerExt record);

    int insertSelective(LecturerExt record);

    List<LecturerExt> selectByExample(LecturerExtExample example);

    LecturerExt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LecturerExt record, @Param("example") LecturerExtExample example);

    int updateByExample(@Param("record") LecturerExt record, @Param("example") LecturerExtExample example);

    int updateByPrimaryKeySelective(LecturerExt record);

    int updateByPrimaryKey(LecturerExt record);
}