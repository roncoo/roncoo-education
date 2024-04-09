package com.roncoo.education.system.test;

import com.roncoo.education.system.service.admin.biz.AdminSysUserBiz;
import com.roncoo.education.system.service.admin.req.AdminSysUserSaveReq;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class UsersTest extends BaseTest {

    @Resource
    private AdminSysUserBiz adminSysUserBiz;

    @Test
    public void save() {
        AdminSysUserSaveReq req = new AdminSysUserSaveReq();
        req.setMobile("18302045627");
        req.setMobilePwd("RonCoo.123");
        req.setRePassword("RonCoo.123");
        req.setRealName("超级管理员");
        adminSysUserBiz.save(req);
    }
}
