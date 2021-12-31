package com.roncoo.education.system.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.auth.biz.AuthApiMsgUserBiz;
import com.roncoo.education.system.service.auth.bo.MsgReadBO;
import com.roncoo.education.system.service.auth.bo.MsgUserBO;
import com.roncoo.education.system.service.auth.bo.MsgViewBO;
import com.roncoo.education.system.service.auth.dto.MsgDTO;
import com.roncoo.education.system.service.auth.dto.MsgReadDTO;
import com.roncoo.education.system.service.auth.dto.MsgUserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/system/auth/msg/user")
public class AuthApiMsgUser extends BaseController {

    @Autowired
    private AuthApiMsgUserBiz biz;

    /**
     * 站内信分页列表接口
     *
     * @param msgUserBO
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "学员站内信分页列表接口", notes = "分页列出学员站内信信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    Result<Page<MsgUserDTO>> list(@RequestBody MsgUserBO msgUserBO) {
        return biz.list(msgUserBO);
    }

    /**
     * 用户查看站内信内容
     *
     * @param MsgViewBO
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "用户查看站内信", notes = "用户查看站内信")
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    Result<MsgDTO> readMsg(@RequestBody MsgViewBO MsgViewBO) {
        return biz.readMsg(MsgViewBO);
    }

    /**
     * 用户站内信未读条数
     *
     * @param msgReadBO
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "用户站内信未读条数", notes = "用户站内信未读条数")
    @RequestMapping(value = "/num", method = RequestMethod.POST)
    Result<MsgReadDTO> getNumOfUnReadMsg(@RequestBody MsgReadBO msgReadBO) {
        return biz.getNumOfUnReadMsg(msgReadBO);
    }

}
