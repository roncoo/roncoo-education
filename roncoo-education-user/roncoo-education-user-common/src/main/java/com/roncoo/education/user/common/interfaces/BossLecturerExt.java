package com.roncoo.education.user.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.LecturerExtQO;
import com.roncoo.education.user.common.bean.vo.LecturerExtVO;
import com.roncoo.education.util.base.Page;

/**
 * 讲师账户信息表 
 *
 * @author wujing
 */
public interface BossLecturerExt {
	
	@RequestMapping(value = "/boss/user/lecturerExt/listForPage", method = RequestMethod.POST)
	Page<LecturerExtVO> listForPage(@RequestBody LecturerExtQO qo);
	
	@RequestMapping(value = "/boss/user/lecturerExt/save", method = RequestMethod.POST)
	int save(@RequestBody LecturerExtQO qo);
	
	@RequestMapping(value = "/boss/user/lecturerExt/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/user/lecturerExt/update", method = RequestMethod.PUT)
	int updateById(@RequestBody LecturerExtQO qo);
	
	@RequestMapping(value = "/boss/user/lecturerExt/get/{id}", method = RequestMethod.GET)
	LecturerExtVO getById(@PathVariable(value = "id") Long id);
	
	/**
	 * 根据讲师编号查找账户信息（订单回调远程调用）
	 * 
	 * @param lecturerUserNo
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/user/lecturerExt/getByLecturerUserNo/{lecturerUserNo}", method = RequestMethod.GET)
	LecturerExtVO getByLecturerUserNo(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo);
	
	/**
	 * 更新账户的金额信息(订单回调远程调用)
	 * 
	 * @param qo
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/user/lecturerExt/updateTotalIncomeByLecturerUserNo", method = RequestMethod.PUT)
	void updateTotalIncomeByLecturerUserNo(@RequestBody LecturerExtQO qo);
}
