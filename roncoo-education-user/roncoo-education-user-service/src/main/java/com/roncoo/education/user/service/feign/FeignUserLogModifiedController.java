package com.roncoo.education.user.service.feign;

import com.roncoo.education.user.feign.interfaces.IFeignUserLogModified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.user.feign.qo.UserLogModifiedQO;
import com.roncoo.education.user.feign.vo.UserLogModifiedVO;
import com.roncoo.education.user.service.feign.biz.FeignUserLogModifiedBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 用户修改日志
 *
 * @author wujing
 */
@RestController
public class FeignUserLogModifiedController extends BaseController implements IFeignUserLogModified {

	@Autowired
	private FeignUserLogModifiedBiz biz;

	@Override
	public Page<UserLogModifiedVO> listForPage(@RequestBody UserLogModifiedQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody UserLogModifiedQO qo){
		return biz.save(qo);
	}

    @Override
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}

    @Override
	public int updateById(@RequestBody UserLogModifiedQO qo){
		return biz.updateById(qo);
	}

    @Override
	public UserLogModifiedVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}

}
