package com.roncoo.education.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.qo.UserExtEchartsQO;
import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.user.common.bean.vo.UserEchartsVO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.common.interfaces.BossUserExt;
import com.roncoo.education.user.service.controller.biz.BossUserExtBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@RestController
public class BossUserExtController extends BaseController implements BossUserExt {

	@Autowired
	private BossUserExtBiz biz;

	@Override
	public Page<UserExtVO> listForPage(@RequestBody UserExtQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody UserExtQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody UserExtQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public UserExtVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override

	public UserExtVO getByUserNo(@PathVariable(value = "userNo") Long userNo) {
		return biz.getByUserNo(userNo);
	}

	@Override
	public List<UserEchartsVO> sumByCounts(@RequestBody UserExtEchartsQO userExtEchartsQO) {
		return biz.sumByCounts(userExtEchartsQO);
	}

	@Override
	public void cachUserForMsg() {
		biz.cachUserForMsg();
	}

}
