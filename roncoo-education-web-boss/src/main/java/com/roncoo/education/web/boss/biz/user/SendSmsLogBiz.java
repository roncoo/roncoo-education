package com.roncoo.education.web.boss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.common.bean.qo.SendSmsLogQO;
import com.roncoo.education.user.common.bean.vo.SendSmsLogVO;
import com.roncoo.education.user.feign.IBossSendSmsLog;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@Component
public class SendSmsLogBiz extends BaseBiz {

	@Autowired
	private IBossSendSmsLog bossSendSmsLog;

	public Page<SendSmsLogVO> listForPage(SendSmsLogQO qo) {
		return bossSendSmsLog.listForPage(qo);
	}

	public int save(SendSmsLogQO qo) {
		return bossSendSmsLog.save(qo);
	}

	public int deleteById(Long id) {
		return bossSendSmsLog.deleteById(id);
	}

	public SendSmsLogVO getById(Long id) {
		return bossSendSmsLog.getById(id);
	}

	public int updateById(SendSmsLogQO qo) {
		return bossSendSmsLog.updateById(qo);
	}

	public int send(SendSmsLogQO qo) {
		return bossSendSmsLog.send(qo);
	}

}
