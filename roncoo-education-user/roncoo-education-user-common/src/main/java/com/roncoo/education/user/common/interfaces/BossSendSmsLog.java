package com.roncoo.education.user.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.user.common.bean.qo.SendSmsLogQO;
import com.roncoo.education.user.common.bean.vo.SendSmsLogVO;
import com.roncoo.education.util.base.Page;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
public interface BossSendSmsLog {

	@RequestMapping(value = "/boss/user/sendSmsLog/listForPage")
	Page<SendSmsLogVO> listForPage(@RequestBody SendSmsLogQO qo);

	@RequestMapping(value = "/boss/user/sendSmsLog/save")
	int save(@RequestBody SendSmsLogQO qo);

	@RequestMapping(value = "/boss/user/sendSmsLog/deleteById")
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/user/sendSmsLog/updateById")
	int updateById(@RequestBody SendSmsLogQO qo);

	@RequestMapping(value = "/boss/user/sendSmsLog/getById")
	SendSmsLogVO getById(@RequestBody Long id);

	/**
	 * 用户发送短信日志
	 * 
	 * @param qo
	 * @return
	 * @author YZJ
	 */
	@RequestMapping(value = "/boss/user/sendSmsLog/send")
	int send(@RequestBody SendSmsLogQO qo);

}
