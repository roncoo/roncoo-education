package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.UserLogModified;
import com.roncoo.education.user.dao.impl.mapper.entity.UserLogModifiedExample;

public interface UserLogModifiedDao {
    int save(UserLogModified record);

    int deleteById(Long id);

    int updateById(UserLogModified record);

    UserLogModified getById(Long id);

    Page<UserLogModified> listForPage(int pageCurrent, int pageSize, UserLogModifiedExample example);
}
