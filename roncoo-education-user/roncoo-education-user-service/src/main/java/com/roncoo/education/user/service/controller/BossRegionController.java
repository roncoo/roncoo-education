package com.roncoo.education.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.user.common.bean.qo.RegionQO;
import com.roncoo.education.user.common.bean.vo.RegionVO;
import com.roncoo.education.user.common.interfaces.BossRegion;
import com.roncoo.education.user.service.controller.biz.BossRegionBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 行政区域表 
 *
 * @author wujing
 */
@RestController
public class BossRegionController extends BaseController implements BossRegion{

	@Autowired
	private BossRegionBiz biz;
	
	@Override
	public Page<RegionVO> listForPage(@RequestBody RegionQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody RegionQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody RegionQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public RegionVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
	
}
