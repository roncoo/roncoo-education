package com.roncoo.education.user.service.feign.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.feign.qo.RegionQO;
import com.roncoo.education.user.feign.vo.RegionVO;
import com.roncoo.education.user.service.dao.RegionDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Region;
import com.roncoo.education.user.service.dao.impl.mapper.entity.RegionExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Component
public class FeignRegionBiz {

	@Autowired
	private RegionDao dao;

	public Page<RegionVO> listForPage(RegionQO qo) {
	    RegionExample example = new RegionExample();
	    example.setOrderByClause(" id desc ");
        Page<Region> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, RegionVO.class);
	}

	public int save(RegionQO qo) {
	    Region record = BeanUtil.copyProperties(qo, Region.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public RegionVO getById(Long id) {
	    Region record = dao.getById(id);
		return BeanUtil.copyProperties(record, RegionVO.class);
	}

	public int updateById(RegionQO qo) {
	    Region record = BeanUtil.copyProperties(qo, Region.class);
		return dao.updateById(record);
	}

}
