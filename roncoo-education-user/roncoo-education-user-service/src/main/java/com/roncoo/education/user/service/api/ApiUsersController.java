package com.roncoo.education.user.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiUsersBiz;
import com.roncoo.education.user.service.api.req.PasswordReq;
import com.roncoo.education.user.service.api.req.RegisterReq;
import com.roncoo.education.user.service.api.resp.UsersLoginResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/api/users")
public class ApiUsersController {

    @Autowired
    private ApiUsersBiz biz;

    /**
     * 注册接口
     */
    @ApiOperation(value = "注册接口", notes = "注册成功返回登录信息")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<UsersLoginResp> register(@RequestBody RegisterReq req) {
        return biz.register(req);
    }

    /**
     * 用户密码登录接口
     */
    @ApiOperation(value = "登录接口", notes = "密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<UsersLoginResp> loginPassword(@RequestBody PasswordReq req) {
        return biz.loginPassword(req);
    }

}
