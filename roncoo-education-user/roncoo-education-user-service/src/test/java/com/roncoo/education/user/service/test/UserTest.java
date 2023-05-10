/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.test;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户注册
 */
public class UserTest extends BaseTest {

    private static final String MOBILE = "18812345678";
    private static final String PASSWORD = "123456";

    @Autowired
    private UsersDao usersDao;

    @Test
    public void add() {
        // 用户基本信息
        Users user = new Users();
        user.setMobile(MOBILE);
        user.setMobileSalt(IdUtil.simpleUUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + PASSWORD));
        usersDao.save(user);
    }

}
