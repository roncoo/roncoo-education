package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthMsgUserBiz;
import com.roncoo.education.user.service.auth.req.AuthMsgUserGetReq;
import com.roncoo.education.user.service.auth.req.AuthMsgUserPageReq;
import com.roncoo.education.user.service.auth.resp.AuthMsgResp;
import com.roncoo.education.user.service.auth.resp.AuthMsgUserResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-站内信用户记录表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "auth-站内信用户记录表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/msg/user")
public class AuthMsgUserController {

    @NotNull
    private final AuthMsgUserBiz biz;

    /**
     * 站内信分页列表接口
     *
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "学员站内信分页列表接口", notes = "分页列出学员站内信信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    Result<Page<AuthMsgUserResp>> list(@RequestBody AuthMsgUserPageReq req) {
        return biz.list(req);
    }

    /**
     * 用户查看站内信内容
     *
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "用户查看站内信", notes = "用户查看站内信")
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    Result<AuthMsgResp> readMsg(@RequestBody AuthMsgUserGetReq req) {
        return biz.readMsg(req);
    }

}
