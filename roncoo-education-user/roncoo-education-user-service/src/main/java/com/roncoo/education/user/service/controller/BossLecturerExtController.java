package com.roncoo.education.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.user.common.bean.qo.LecturerExtQO;
import com.roncoo.education.user.common.bean.vo.LecturerExtVO;
import com.roncoo.education.user.common.interfaces.BossLecturerExt;
import com.roncoo.education.user.service.controller.biz.BossLecturerExtBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 讲师账户信息表
 *
 * @author wujing
 */
@RestController
public class BossLecturerExtController extends BaseController implements BossLecturerExt {

	@Autowired
	private BossLecturerExtBiz biz;

	@Override
	public Page<LecturerExtVO> listForPage(@RequestBody LecturerExtQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody LecturerExtQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody LecturerExtQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public LecturerExtVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public LecturerExtVO getByLecturerUserNo(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo) {
		return biz.getByLecturerUserNo(lecturerUserNo);
	}

	@Override
	public void updateTotalIncomeByLecturerUserNo(@RequestBody LecturerExtQO qo) {
		biz.updateTotalIncomeByLecturerUserNo(qo);
	}

}
