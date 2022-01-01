package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExtExample;

public interface UserExtDao {
    int save(UserExt record);

    int deleteById(Long id);

    int updateById(UserExt record);

    UserExt getById(Long id);

    Page<UserExt> listForPage(int pageCurrent, int pageSize, UserExtExample example);

    UserExt getByUserNo(Long userNo);

    int updateByUserNo(UserExt record);

    /**
     * 根据手机号码获取用户信息
     *
     * @param mobile
     * @return
     * @author wuyun
     */
    UserExt getByMobile(String mobile);

    /**
     * 获取用户注册量
     *
     * @param date
     * @return
     * @author wuyun
     */
    Integer sumByCountOrders(String date);

}
