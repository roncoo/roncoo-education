package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExample;
import com.roncoo.education.util.base.Page;

public interface UserDao {
    int save(User record);

    int deleteById(Long id);

    int updateById(User record);

    User getById(Long id);

    Page<User> listForPage(int pageCurrent, int pageSize, UserExample example);

	User getByMobile(String mobile);

	User getByUserNo(Long userNo);
}