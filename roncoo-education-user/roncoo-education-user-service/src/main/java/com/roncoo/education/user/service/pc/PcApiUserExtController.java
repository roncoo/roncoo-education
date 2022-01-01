package com.roncoo.education.user.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.pc.biz.PcApiUserExtBiz;
import com.roncoo.education.user.service.pc.req.UserExtPageREQ;
import com.roncoo.education.user.service.pc.req.UserExtUpdateREQ;
import com.roncoo.education.user.service.pc.req.UserExtViewREQ;
import com.roncoo.education.user.service.pc.resq.UserExtPageRESQ;
import com.roncoo.education.user.service.pc.resq.UserExtViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户教育信息
 */
@RestController
@RequestMapping(value = "/user/pc/user/ext")
public class PcApiUserExtController extends BaseController {

    @Autowired
    private PcApiUserExtBiz biz;

    /**
     * 用戶教育分页列出接口
     */
    @ApiOperation(value = "用戶教育分页列出接口", notes = "用戶教育分页列出接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<UserExtPageRESQ>> listForPage(@RequestBody UserExtPageREQ userExtREQ) {
        return biz.listForPage(userExtREQ);
    }

    /**
     * 用戶教育更新接口
     */
    @ApiOperation(value = "用戶教育更新接口", notes = "用戶教育更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody UserExtUpdateREQ userExtUpdateREQ) {
        return biz.update(userExtUpdateREQ);
    }

    /**
     * 用戶教育分页列出接口
     */
    @ApiOperation(value = "查看用户教育信息接口", notes = "查看用户教育信息接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<UserExtViewRESQ> view(@RequestBody UserExtViewREQ userExtViewREQ) {
        return biz.view(userExtViewREQ);
    }

}
