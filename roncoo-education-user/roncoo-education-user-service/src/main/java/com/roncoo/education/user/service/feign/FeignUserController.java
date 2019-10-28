package com.roncoo.education.user.service.feign;

import com.roncoo.education.user.feign.interfaces.IFeignUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.feign.qo.UserQO;
import com.roncoo.education.user.feign.vo.UserVO;
import com.roncoo.education.user.service.feign.biz.FeignUserBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@RestController
public class FeignUserController extends BaseController implements IFeignUser {

	@Autowired
	private FeignUserBiz biz;

	@Override
	public Page<UserVO> listForPage(@RequestBody UserQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody UserQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody UserQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public UserVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public UserVO getByUserNo(@PathVariable(value = "userNo") Long userNo) {
		return biz.getByUserNo(userNo);
	}

	@Override
	public UserVO getByMobile(@PathVariable(value = "mobile") String mobile) {
		return biz.getByMobile(mobile);
	}

}
