package com.roncoo.education.web.boss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.common.bean.qo.UserLogLoginQO;
import com.roncoo.education.user.common.bean.vo.UserLogLoginVO;
import com.roncoo.education.user.feign.IBossUserLogLogin;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 用户错误登录日志 
 *
 * @author wujing
 */
@Component
public class UserLogLoginBiz extends BaseBiz{

	@Autowired
	private IBossUserLogLogin bossUserLogLogin;

	public Page<UserLogLoginVO> listForPage(UserLogLoginQO qo) {
        return bossUserLogLogin.listForPage(qo);
	}

	public int save(UserLogLoginQO qo) {
		return bossUserLogLogin.save(qo);
	}

	public int deleteById(Long id) {
		return bossUserLogLogin.deleteById(id);
	}

	public UserLogLoginVO getById(Long id) {
		return bossUserLogLogin.getById(id);
	}

	public int updateById(UserLogLoginQO qo) {
		return bossUserLogLogin.updateById(qo);
	}
	
}
