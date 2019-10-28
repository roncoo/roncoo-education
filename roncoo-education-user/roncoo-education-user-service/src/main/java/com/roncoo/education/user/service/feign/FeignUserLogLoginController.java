package com.roncoo.education.user.service.feign;

import com.roncoo.education.user.feign.interfaces.IFeignUserLogLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.user.feign.qo.UserLogLoginQO;
import com.roncoo.education.user.feign.vo.UserLogLoginVO;
import com.roncoo.education.user.service.feign.biz.FeignUserLogLoginBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 用户错误登录日志
 *
 * @author wujing
 */
@RestController
public class FeignUserLogLoginController extends BaseController implements IFeignUserLogLogin {

	@Autowired
	private FeignUserLogLoginBiz biz;

	@Override
	public Page<UserLogLoginVO> listForPage(@RequestBody UserLogLoginQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody UserLogLoginQO qo){
		return biz.save(qo);
	}

    @Override
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}

    @Override
	public int updateById(@RequestBody UserLogLoginQO qo){
		return biz.updateById(qo);
	}

    @Override
	public UserLogLoginVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}

}
