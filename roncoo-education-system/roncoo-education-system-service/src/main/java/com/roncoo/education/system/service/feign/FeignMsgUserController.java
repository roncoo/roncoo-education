package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignMsgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.feign.qo.MsgUserQO;
import com.roncoo.education.system.feign.vo.MsgUserVO;
import com.roncoo.education.system.service.feign.biz.FeignMsgUserBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;


/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@RestController
public class FeignMsgUserController extends BaseController implements IFeignMsgUser {

	@Autowired
	private FeignMsgUserBiz biz;

	@Override
	public Page<MsgUserVO> listForPage(@RequestBody MsgUserQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody MsgUserQO qo){
		return biz.save(qo);
	}

    @Override
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}

    @Override
	public int updateById(@RequestBody MsgUserQO qo){
		return biz.updateById(qo);
	}

    @Override
	public MsgUserVO getById(@RequestBody Long id){
		return biz.getById(id);
	}

}
