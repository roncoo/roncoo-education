package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample;

import java.util.List;

public interface UsersDao {
    int save(Users record);

    int deleteById(Long id);

    int updateById(Users record);

    Users getById(Long id);

    Page<Users> page(int pageCurrent, int pageSize, UsersExample example);

    Users getByMobile(String mobile);

    List<Users> listByIds(List<Long> userIdList);
}
