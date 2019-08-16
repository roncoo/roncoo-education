package com.roncoo.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.common.bean.qo.SysUserQO;
import com.roncoo.education.system.common.bean.vo.SysUserVO;
import com.roncoo.education.system.common.interfaces.BossSysUser;
import com.roncoo.education.system.service.controller.biz.BossSysUserBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 后台用户信息 
 *
 * @author wujing
 */
@RestController
public class BossSysUserController extends BaseController implements BossSysUser{

	@Autowired
	private BossSysUserBiz biz;
	
	@Override
	public Page<SysUserVO> listForPage(@RequestBody SysUserQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody SysUserQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody SysUserQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public SysUserVO getById(@RequestBody Long id){
		return biz.getById(id);
	}
	
}
