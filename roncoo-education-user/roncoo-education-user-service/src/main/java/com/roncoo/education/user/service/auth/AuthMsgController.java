package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthMsgBiz;
import com.roncoo.education.user.service.auth.req.AuthMsgReq;
import com.roncoo.education.user.service.auth.resp.AuthMsgResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-站内信息表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-站内信息表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/msg")
public class AuthMsgController {

    @NotNull
    private final AuthMsgBiz biz;

    /**
     * 课站内信详情
     */
    @ApiOperation(value = "站内信详情", notes = "站内信详情接口")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    Result<AuthMsgResp> get(@RequestBody AuthMsgReq req) {
        return biz.get(req);
    }

}
