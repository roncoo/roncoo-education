package com.roncoo.education.system.test;

import com.roncoo.education.system.service.admin.biz.AdminSysUserBiz;
import com.roncoo.education.system.service.admin.req.SysUserSaveREQ;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersTest extends  BaseTest{

    @Autowired
    private AdminSysUserBiz adminSysUserBiz;

    @Test
    public void save() {
        SysUserSaveREQ req = new SysUserSaveREQ();
        req.setMobile("18302045627");
        req.setPassword("RonCoo.123");
        req.setRepassword("RonCoo.123");
        req.setRealName("超级管理员");
        adminSysUserBiz.save(req);
    }
}
