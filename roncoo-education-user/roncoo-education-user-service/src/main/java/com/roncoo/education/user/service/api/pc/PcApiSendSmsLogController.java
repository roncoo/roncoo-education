package com.roncoo.education.user.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.api.pc.biz.PcApiSendSmsLogBiz;
import com.roncoo.education.user.common.req.SendSmsLogPageREQ;
import com.roncoo.education.user.common.req.SendSmsLogSendREQ;
import com.roncoo.education.user.common.resq.SendSmsLogPageRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

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
