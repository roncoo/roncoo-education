package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LecturerMapper {
    int countByExample(LecturerExample example);

    int deleteByExample(LecturerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Lecturer record);

    int insertSelective(Lecturer record);

    List<Lecturer> selectByExample(LecturerExample example);

    Lecturer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Lecturer record, @Param("example") LecturerExample example);

    int updateByExample(@Param("record") Lecturer record, @Param("example") LecturerExample example);

    int updateByPrimaryKeySelective(Lecturer record);

    int updateByPrimaryKey(Lecturer record);
}