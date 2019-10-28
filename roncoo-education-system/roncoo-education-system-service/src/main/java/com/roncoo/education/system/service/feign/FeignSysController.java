package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.feign.qo.SysQO;
import com.roncoo.education.system.feign.vo.SysVO;
import com.roncoo.education.system.service.feign.biz.FeignSysBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 系统配置表
 *
 * @author YZJ
 */
@RestController
public class FeignSysController extends BaseController implements IFeignSys {

	@Autowired
	private FeignSysBiz biz;

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
