package com.roncoo.education.user.common.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.UserExtEchartsQO;
import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.user.common.bean.vo.UserEchartsVO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.util.base.Page;

/**
 * 用户教育信息
 *
 * @author wujing
 */
public interface BossUserExt {

	@RequestMapping(value = "/boss/user/userExt/listForPage", method = RequestMethod.POST)
	Page<UserExtVO> listForPage(@RequestBody UserExtQO qo);

	@RequestMapping(value = "/boss/user/userExt/save", method = RequestMethod.POST)
	int save(@RequestBody UserExtQO qo);

	@RequestMapping(value = "/boss/user/userExt/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/userExt/update", method = RequestMethod.PUT)
	int updateById(@RequestBody UserExtQO qo);

	@RequestMapping(value = "/boss/user/userExt/get/{id}", method = RequestMethod.GET)
	UserExtVO getById(@PathVariable(value = "id") Long id);

	/**
	 * 根据userNo查找用户教育信息
	 */
	@RequestMapping(value = "/boss/user/userExt/get/userNo/{userNo}", method = RequestMethod.GET)
	UserExtVO getByUserNo(@PathVariable(value = "userNo") Long userNo);

	/**
	 * 获取用户注册量
	 * 
	 * @param userExtEchartsQO
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/user/userExt/sumByCounts", method = RequestMethod.POST)
	List<UserEchartsVO> sumByCounts(@RequestBody UserExtEchartsQO userExtEchartsQO);

	/**
	 * 批量缓存用户信息,发送站内信用
	 * 
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/user/userExt/cachUserForMsg")
	void cachUserForMsg();
}
