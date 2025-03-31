package com.roncoo.education.user.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiUsersBiz;
import com.roncoo.education.user.service.api.req.*;
import com.roncoo.education.user.service.api.resp.UsersLoginResp;
import com.roncoo.education.user.service.api.resp.WxCodeResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Api(tags = "api-用户登录注册")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user/api/users")
public class ApiUsersController {

    @NotNull
    private final ApiUsersBiz biz;

    /**
     * 验证码发送接口(注册验证码 + 重置密码验证码)
     */
    @ApiOperation(value = "验证码发送接口", notes = "发送手机验证码")
    @RequestMapping(value = "/send/code", method = RequestMethod.POST)
    public Result<String> sendCode(@RequestBody SendCodeReq req) {
        return biz.sendCode(req);
    }

    /**
     * 用户密码注册接口
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


    @ApiOperation(value = "微信登录，获取授权地址", notes = "返回微信登录授权地址")
    @PostMapping(value = "/wx/login")
    public Result<String> wxLogin(@RequestBody WxLoginReq req) {
        return biz.wxLogin(req);
    }

    @ApiOperation(value = "微信登录，获取登录信息", notes = "返回用登录信息")
    @PostMapping(value = "/wx/code")
    public Result<WxCodeResp> wxCode(@RequestBody WxCodeReq req) throws WxErrorException {
        return biz.wxCode(req);
    }

    @ApiOperation(value = "微信登录，绑定用户信息", notes = "返回用登录信息")
    @PostMapping(value = "/wx/binding")
    public Result<UsersLoginResp> wxBinding(@RequestBody WxBindingReq req) {
        return biz.wxBinding(req);
    }

    @ApiOperation(value = "支付宝登录，获取授权地址", notes = "返回支付宝登录授权地址")
    @PostMapping(value = "/alipay/login")
    public Result<String> alipayLogin(@RequestBody AlipayLoginReq req) {
        return biz.alipayLogin(req);
    }

    @ApiOperation(value = "支付宝登录，获取登录信息", notes = "返回用登录信息")
    @PostMapping(value = "/alipay/code")
    public Result<WxCodeResp> alipayCode(@RequestBody AlipayCodeReq req) throws Exception {
        return biz.alipayCode(req);
    }

    @ApiOperation(value = "支付宝登录，绑定用户信息", notes = "返回用登录信息")
    @PostMapping(value = "/alipay/binding")
    public Result<UsersLoginResp> alipayBinding(@RequestBody AlipayBindingReq req) {
        return biz.alipayBinding(req);
    }
}
