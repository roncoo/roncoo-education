package com.roncoo.education.user.service.pc;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.pc.biz.PcApiUserLoginBiz;
import com.roncoo.education.user.service.pc.req.UserLoginPasswordREQ;
import com.roncoo.education.user.service.pc.resq.UserLoginRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户接口
 */
@RestController
@RequestMapping(value = "/user/pc/api/user/login")
public class PcApiUserLoginController {

    @Autowired
    private PcApiUserLoginBiz biz;

    /**
     * 用户密码登录接口
     */
    @ApiOperation(value = "用户密码登录接口", notes = "用户密码登录")
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Result<UserLoginRESQ> loginPassword(@RequestBody UserLoginPasswordREQ userLoginPasswordREQ) {
        return biz.loginPassword(userLoginPasswordREQ);
    }

}
