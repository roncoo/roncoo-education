package com.roncoo.education.user.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.UserLogModifiedQO;
import com.roncoo.education.user.common.bean.vo.UserLogModifiedVO;
import com.roncoo.education.util.base.Page;

/**
 * 用户修改日志 
 *
 * @author wujing
 */
public interface BossUserLogModified {
	
	@RequestMapping(value = "/boss/user/userLogModified/listForPage", method = RequestMethod.POST)
	Page<UserLogModifiedVO> listForPage(@RequestBody UserLogModifiedQO qo);
	
	@RequestMapping(value = "/boss/user/userLogModified/save", method = RequestMethod.POST)
	int save(@RequestBody UserLogModifiedQO qo);
	
	@RequestMapping(value = "/boss/user/userLogModified/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/user/userLogModified/update", method = RequestMethod.PUT)
	int updateById(@RequestBody UserLogModifiedQO qo);
	
	@RequestMapping(value = "/boss/user/userLogModified/get/{id}", method = RequestMethod.GET)
	UserLogModifiedVO getById(@PathVariable(value = "id") Long id);
	
}
