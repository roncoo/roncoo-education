package com.roncoo.education.user.dao.impl.mapper;

import com.roncoo.education.user.dao.impl.mapper.entity.LecturerAudit;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerAuditExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LecturerAuditMapper {
    int countByExample(LecturerAuditExample example);

    int deleteByExample(LecturerAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LecturerAudit record);

    int insertSelective(LecturerAudit record);

    List<LecturerAudit> selectByExample(LecturerAuditExample example);

    LecturerAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LecturerAudit record, @Param("example") LecturerAuditExample example);

    int updateByExample(@Param("record") LecturerAudit record, @Param("example") LecturerAuditExample example);

    int updateByPrimaryKeySelective(LecturerAudit record);

    int updateByPrimaryKey(LecturerAudit record);
}
