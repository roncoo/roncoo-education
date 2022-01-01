package com.roncoo.education.user.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.pc.biz.PcApiSendSmsLogBiz;
import com.roncoo.education.user.service.pc.req.SendSmsLogPageREQ;
import com.roncoo.education.user.service.pc.req.SendSmsLogSendREQ;
import com.roncoo.education.user.service.pc.resq.SendSmsLogPageRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信记录
 */
@RestController
@RequestMapping(value = "/user/pc/send/sms/log")
public class PcApiSendSmsLogController extends BaseController {

    @Autowired
    private PcApiSendSmsLogBiz biz;

    /**
     * 短信记录分页列表接口
     *
     * @param sendSmsLogPageREQ
     */
    @ApiOperation(value = "短信记录分页列表接口", notes = "短信记录分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<SendSmsLogPageRESQ>> listForPage(@RequestBody SendSmsLogPageREQ sendSmsLogPageREQ) {
        return biz.listForPage(sendSmsLogPageREQ);
    }

    /**
     * 发送验证码接口
     *
     * @param sendSmsLogSendREQ
     */
    @ApiOperation(value = "发送验证码接口", notes = "发送验证码接口")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Result<Integer> send(@RequestBody SendSmsLogSendREQ sendSmsLogSendREQ) {
        return biz.send(sendSmsLogSendREQ);
    }

}
