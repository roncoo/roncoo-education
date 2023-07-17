package com.roncoo.education.user.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiUsersBiz;
import com.roncoo.education.user.service.api.req.LoginReq;
import com.roncoo.education.user.service.api.req.PasswordReq;
import com.roncoo.education.user.service.api.req.RegisterReq;
import com.roncoo.education.user.service.api.req.SendCodeReq;
import com.roncoo.education.user.service.api.resp.UsersLoginResp;
import io.swagger.annotations.Api;
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
@Api(tags = "api-用户登录注册")
@RestController
@RequestMapping(value = "/user/api/users")
public class ApiUsersController {

    @Autowired
    private ApiUsersBiz biz;

    /**
     * 注册验证码发送接口
     */
    @ApiOperation(value = "注册验证码发送接口", notes = "发送手机验证码")
    @RequestMapping(value = "/send/code", method = RequestMethod.POST)
    public Result<String> sendCode(@RequestBody SendCodeReq req) {
        return biz.sendCode(req);
    }

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
    public Result<UsersLoginResp> loginPassword(@RequestBody LoginReq req) {
        return biz.login(req);
    }

    /**
     * 用户密码登录接口
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Result<String> password(@RequestBody PasswordReq req) {
        return biz.password(req);
    }

}
