package com.roncoo.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.qo.MsgQO;
import com.roncoo.education.system.common.bean.vo.MsgVO;
import com.roncoo.education.system.common.interfaces.BossMsg;
import com.roncoo.education.system.service.controller.biz.BossMsgBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;


/**
 * 站内信息表
 *
 * @author wuyun
 */
@RestController
public class BossMsgController extends BaseController implements BossMsg {

	@Autowired
	private BossMsgBiz biz;

	@Override
	public Page<MsgVO> listForPage(@RequestBody MsgQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody MsgQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody MsgQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public MsgVO getById(@RequestBody Long id) {
		return biz.getById(id);
	}
	
	@Override
	public int push() {
		return biz.push();
	}

	@Override
	public int pushByManual(@RequestBody Long id) {
		return biz.pushByManual(id);
	}
}
