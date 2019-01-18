package com.roncoo.education.web.boss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.common.bean.qo.UserLogModifiedQO;
import com.roncoo.education.user.common.bean.vo.UserLogModifiedVO;
import com.roncoo.education.user.feign.IBossUserLogModified;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 用户修改日志 
 *
 * @author wujing
 */
@Component
public class UserLogModifiedBiz extends BaseBiz{

	@Autowired
	private IBossUserLogModified bossUserLogModified;

	public Page<UserLogModifiedVO> listForPage(UserLogModifiedQO qo) {
        return bossUserLogModified.listForPage(qo);
	}

	public int save(UserLogModifiedQO qo) {
		return bossUserLogModified.save(qo);
	}

	public int deleteById(Long id) {
		return bossUserLogModified.deleteById(id);
	}

	public UserLogModifiedVO getById(Long id) {
		return bossUserLogModified.getById(id);
	}

	public int updateById(UserLogModifiedQO qo) {
		return bossUserLogModified.updateById(qo);
	}
	
}
