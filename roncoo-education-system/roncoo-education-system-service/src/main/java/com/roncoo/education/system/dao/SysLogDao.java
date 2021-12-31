package com.roncoo.education.system.dao;

import com.roncoo.education.system.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample;
import com.roncoo.education.common.core.base.Page;

public interface SysLogDao {
    int save(SysLog record);

    int deleteById(Long id);

    int updateById(SysLog record);

    int updateByExampleSelective(SysLog record, SysLogExample example);

    SysLog getById(Long id);

    Page<SysLog> listForPage(int pageCurrent, int pageSize, SysLogExample example);
}
