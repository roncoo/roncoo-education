package com.roncoo.education.user.dao;

import com.roncoo.education.common.base.page.Page;
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

    Integer count(UsersExample example);

    List<Users> listByMobile(String mobile);

    /**
     * 根据unionId或openId查询用户，优先查询unionId
     *
     * @param unionId
     * @param openId
     * @return
     */
    Users getByUnionIdOrOpenId(String unionId, String openId);
}
