package com.roncoo.education.user.service.biz.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.service.common.req.UserPageREQ;
import com.roncoo.education.user.service.common.resq.UserPageRESQ;
import com.roncoo.education.user.service.dao.UserDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExample;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;

/**
 * 用户信息
 */
@Component
public class PcApiUserBiz {

	@Autowired
	private UserDao dao;

	/**
	 * 用戶分页列出
	 * 
	 * @param userPageREQ
	 * @return
	 */
	public Result<Page<UserPageRESQ>> listForPage(UserPageREQ userPageREQ) {
		UserExample example = new UserExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" status_id desc, id desc ");
		Page<User> page = dao.listForPage(userPageREQ.getPageCurrent(), userPageREQ.getPageSize(), example);
		return Result.success(PageUtil.transform(page, UserPageRESQ.class));
	}
}
