package com.roncoo.education.user.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.pc.biz.PcApiUserBiz;
import com.roncoo.education.user.service.pc.req.UserPageREQ;
import com.roncoo.education.user.service.pc.resq.UserPageRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息
 */
@RestController
@RequestMapping(value = "/user/pc/user")
public class PcApiUserController extends BaseController {

    @Autowired
    private PcApiUserBiz biz;

    /**
     * 用戶分页列出接口
     */
    @ApiOperation(value = "用戶分页列出接口", notes = "用戶分页列出接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<UserPageRESQ>> listForPage(@RequestBody UserPageREQ userPageREQ) {
        return biz.listForPage(userPageREQ);
    }
}
