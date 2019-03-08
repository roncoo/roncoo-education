package com.roncoo.education.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.qo.SendSmsLogQO;
import com.roncoo.education.user.common.bean.vo.SendSmsLogVO;
import com.roncoo.education.user.common.interfaces.BossSendSmsLog;
import com.roncoo.education.user.service.controller.biz.BossSendSmsLogBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@RestController
public class BossSendSmsLogController extends BaseController implements BossSendSmsLog {

	@Autowired
	private BossSendSmsLogBiz biz;

	@Override
	public Page<SendSmsLogVO> listForPage(@RequestBody SendSmsLogQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody SendSmsLogQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody SendSmsLogQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public SendSmsLogVO getById(@RequestBody Long id) {
		return biz.getById(id);
	}

	@Override
	public int send(@RequestBody SendSmsLogQO qo) {
		return biz.send(qo);
	}

}
