package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.Dic;
import com.roncoo.education.course.service.dao.impl.mapper.entity.DicExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DicMapper {
    int countByExample(DicExample example);

    int deleteByExample(DicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Dic record);

    int insertSelective(Dic record);

    List<Dic> selectByExample(DicExample example);

    Dic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Dic record, @Param("example") DicExample example);

    int updateByExample(@Param("record") Dic record, @Param("example") DicExample example);

    int updateByPrimaryKeySelective(Dic record);

    int updateByPrimaryKey(Dic record);
}