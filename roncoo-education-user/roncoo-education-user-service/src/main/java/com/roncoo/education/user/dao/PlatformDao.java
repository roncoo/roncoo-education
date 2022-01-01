package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.dao.impl.mapper.entity.PlatformExample;

public interface PlatformDao {
    int save(Platform record);

    int deleteById(Long id);

    int updateById(Platform record);

    Platform getById(Long id);

    Page<Platform> listForPage(int pageCurrent, int pageSize, PlatformExample example);

    Platform getByClientId(String clientId);

    Platform getByClientName(String clientName);
}
