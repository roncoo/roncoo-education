package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.SendSmsLog;
import com.roncoo.education.user.dao.impl.mapper.entity.SendSmsLogExample;

public interface SendSmsLogDao {
    int save(SendSmsLog record);

    int deleteById(Long id);

    int updateById(SendSmsLog record);

    SendSmsLog getById(Long id);

    Page<SendSmsLog> listForPage(int pageCurrent, int pageSize, SendSmsLogExample example);
}
