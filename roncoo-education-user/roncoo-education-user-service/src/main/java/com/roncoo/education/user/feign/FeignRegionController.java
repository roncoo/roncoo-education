package com.roncoo.education.user.feign;

import com.roncoo.education.user.feign.biz.FeignRegionBiz;
import com.roncoo.education.user.feign.interfaces.IFeignRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.user.feign.interfaces.qo.RegionQO;
import com.roncoo.education.user.feign.interfaces.vo.RegionVO;
import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;

/**
 * 行政区域表
 *
 * @author wujing
 */
@RestController
public class FeignRegionController extends BaseController implements IFeignRegion {

	@Autowired
	private FeignRegionBiz biz;

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