package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthApiUserExtBiz;
import com.roncoo.education.user.service.auth.bo.AuthUserExtBO;
import com.roncoo.education.user.service.auth.bo.AuthUserExtViewBO;
import com.roncoo.education.user.service.auth.dto.AuthUserExtDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/auth/user/ext")
public class AuthApiUserExtController extends BaseController {

    @Autowired
    private AuthApiUserExtBiz biz;

    /**
     * 用户信息查看接口
     */
    @ApiOperation(value = "用户信息查看接口", notes = "根据userNo获取用户信息接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AuthUserExtDTO> view(@RequestBody AuthUserExtViewBO authUserExtViewBO) {
        return biz.view(authUserExtViewBO);
    }

    /**
     * 用户信息更新接口
     */
    @ApiOperation(value = "用户信息更新接口", notes = "用户信息更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<AuthUserExtDTO> update(@RequestBody AuthUserExtBO authUserExtBO) {
        return biz.update(authUserExtBO);
    }

}
