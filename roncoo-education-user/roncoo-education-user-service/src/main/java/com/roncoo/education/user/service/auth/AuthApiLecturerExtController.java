package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthApiLecturerExtBiz;
import com.roncoo.education.user.service.auth.bo.AuthLecturerExtViewBO;
import com.roncoo.education.user.service.auth.bo.AuthUserExtBankBO;
import com.roncoo.education.user.service.auth.dto.AuthLecturerExtViewDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讲师账户信息表
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/auth/lecturer/ext")
public class AuthApiLecturerExtController extends BaseController {

    @Autowired
    private AuthApiLecturerExtBiz biz;

    /**
     * 账户信息详情接口
     *
     * @param authLecturerExtViewBO
     * @author wuyun
     */
    @ApiOperation(value = "账户信息查看接口", notes = "账户信息详情接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AuthLecturerExtViewDTO> view(@RequestBody AuthLecturerExtViewBO authLecturerExtViewBO) {
        return biz.view(authLecturerExtViewBO);
    }

    /**
     * 更新用户银行卡信息接口
     *
     * @param authUserExtBankBO
     * @author wuyun
     */
    @ApiOperation(value = "更新用户银行卡信息", notes = "更新用户银行卡信息接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> updateBackInfo(@RequestBody AuthUserExtBankBO authUserExtBankBO) {
        return biz.updateBackInfo(authUserExtBankBO);
    }

}
