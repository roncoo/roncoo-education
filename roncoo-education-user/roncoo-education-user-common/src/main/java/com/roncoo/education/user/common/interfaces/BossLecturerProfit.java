package com.roncoo.education.user.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.LecturerProfitQO;
import com.roncoo.education.user.common.bean.vo.LecturerProfitVO;
import com.roncoo.education.util.base.Page;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
public interface BossLecturerProfit {

	@RequestMapping(value = "/boss/user/lecturerProfit/listForPage", method = RequestMethod.POST)
	Page<LecturerProfitVO> listForPage(@RequestBody LecturerProfitQO qo);

	@RequestMapping(value = "/boss/user/lecturerProfit/save", method = RequestMethod.POST)
	int save(@RequestBody LecturerProfitQO qo);

	@RequestMapping(value = "/boss/user/lecturerProfit/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/lecturerProfit/update", method = RequestMethod.PUT)
	int updateById(@RequestBody LecturerProfitQO qo);

	@RequestMapping(value = "/boss/user/lecturerProfit/get/{id}", method = RequestMethod.GET)
	LecturerProfitVO getById(@PathVariable(value = "id") Long id);

}
