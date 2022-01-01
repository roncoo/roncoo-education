package com.roncoo.education.user.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiUserInfoBiz;
import com.roncoo.education.user.service.api.bo.*;
import com.roncoo.education.user.service.api.dto.UserLoginDTO;
import com.roncoo.education.user.service.auth.bo.UserUpdateBO;
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
@RequestMapping(value = "/user/api/user")
public class ApiUserInfoController extends BaseController {

    @Autowired
    private ApiUserInfoBiz biz;

    /**
     * 注册接口
     */
    @ApiOperation(value = "注册接口", notes = "注册成功返回登录信息")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<UserLoginDTO> register(@RequestBody UserRegisterBO userRegisterBO) {
        return biz.register(userRegisterBO);
    }

    /**
     * 用户密码登录接口
     */
    @ApiOperation(value = "用户密码登录接口", notes = "用户密码登录")
    @RequestMapping(value = "/login/password", method = RequestMethod.POST)
    public Result<UserLoginDTO> loginPassword(@RequestBody UserLoginPasswordBO userLoginPasswordBO) {
        return biz.loginPassword(userLoginPasswordBO);
    }

    /**
     * 用户验证码登录接口
     */
    @ApiOperation(value = "用户验证码登录接口", notes = "用户验证码登录")
    @RequestMapping(value = "/login/code", method = RequestMethod.POST)
    public Result<UserLoginDTO> loginCode(@RequestBody UserLoginCodeBO userLoginCodeBO) {
        return biz.loginCode(userLoginCodeBO);
    }

    /**
     * 验证码发送接口
     */
    @ApiOperation(value = "验证码发送接口", notes = "发送手机验证码")
    @RequestMapping(value = "/send/code", method = RequestMethod.POST)
    public Result<String> sendCode(@RequestBody UserSendCodeBO userSendCodeBO) {
        return biz.sendCode(userSendCodeBO);
    }

    /**
     * 授权登录接口
     */
    @ApiOperation(value = "授权登录接口", notes = "后台登录到讲师中心时使用")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Result<String> auth(@RequestBody UserAuthBO userAuthBO) {
        return null;
    }

    /**
     * 用户修改密码接口
     *
     * @author wuyun
     */
    @ApiOperation(value = "用户修改密码接口", notes = "用户修改密码接口")
    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public Result<Integer> updatePassword(@RequestBody UserUpdateBO userUpdateBO) {
        return biz.updatePassword(userUpdateBO);
    }

}
