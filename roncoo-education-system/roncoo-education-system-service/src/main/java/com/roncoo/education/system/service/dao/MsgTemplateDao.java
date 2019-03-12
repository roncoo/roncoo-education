package com.roncoo.education.system.service.dao;

import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgTemplateExample;
import com.roncoo.education.util.base.Page;

public interface MsgTemplateDao {
    int save(MsgTemplate record);

    int deleteById(Long id);

    int updateById(MsgTemplate record);

    MsgTemplate getById(Long id);

    Page<MsgTemplate> listForPage(int pageCurrent, int pageSize, MsgTemplateExample example);
}