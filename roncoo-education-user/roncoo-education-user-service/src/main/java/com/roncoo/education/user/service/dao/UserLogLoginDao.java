package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogLoginExample;
import com.roncoo.education.util.base.Page;

public interface UserLogLoginDao {
    int save(UserLogLogin record);

    int deleteById(Long id);

    int updateById(UserLogLogin record);

    UserLogLogin getById(Long id);

    Page<UserLogLogin> listForPage(int pageCurrent, int pageSize, UserLogLoginExample example);
}