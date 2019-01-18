package com.roncoo.education.user.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.UserQO;
import com.roncoo.education.user.common.bean.vo.UserVO;
import com.roncoo.education.util.base.Page;

/**
 * 用户基本信息
 *
 * @author wujing
 */
public interface BossUser {

	@RequestMapping(value = "/boss/user/user/listForPage", method = RequestMethod.POST)
	Page<UserVO> listForPage(@RequestBody UserQO qo);

	@RequestMapping(value = "/boss/user/user/save", method = RequestMethod.POST)
	int save(@RequestBody UserQO qo);

	@RequestMapping(value = "/boss/user/user/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/user/update", method = RequestMethod.PUT)
	int updateById(@RequestBody UserQO qo);

	@RequestMapping(value = "/boss/user/user/get/{id}", method = RequestMethod.GET)
	UserVO getById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/user/getByUserNo/{userNo}", method = RequestMethod.GET)
	UserVO getByUserNo(@PathVariable(value = "userNo") Long userNo);

	@RequestMapping(value = "/boss/user/user/getByMobile/{mobile}", method = RequestMethod.GET)
	UserVO getByMobile(@PathVariable(value = "mobile") String mobile);
}
