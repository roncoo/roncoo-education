package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplateExample;

public interface MsgTemplateDao {
    int save(MsgTemplate record);

    int deleteById(Long id);

    int updateById(MsgTemplate record);

    MsgTemplate getById(Long id);

    Page<MsgTemplate> listForPage(int pageCurrent, int pageSize, MsgTemplateExample example);
}
