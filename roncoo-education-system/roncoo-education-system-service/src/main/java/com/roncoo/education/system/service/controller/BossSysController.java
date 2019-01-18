package com.roncoo.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.qo.SysQO;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.common.interfaces.BossSys;
import com.roncoo.education.system.service.controller.biz.BossSysBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 系统配置表 
 *
 * @author YZJ
 */
@RestController
public class BossSysController extends BaseController implements BossSys{

	@Autowired
	private BossSysBiz biz;
	
	@Override
	public Page<SysVO> listForPage(@RequestBody SysQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody SysQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody SysQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public SysVO getById(@RequestBody Long id){
		return biz.getById(id);
	}

	@Override
	public SysVO getSys() {
		return biz.getSys();
	}
	
}
