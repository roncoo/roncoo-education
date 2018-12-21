package com.roncoo.education.system.service.biz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysQO;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.service.dao.SysDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Sys;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 系统配置表
 *
 * @author YZJ
 */
@Component
public class BossSysBiz {

	@Autowired
	private SysDao dao;

	public Page<SysVO> listForPage(SysQO qo) {
		SysExample example = new SysExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" id desc ");
		Page<Sys> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysVO.class);
	}

	public int save(SysQO qo) {
		Sys record = BeanUtil.copyProperties(qo, Sys.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysVO getById(Long id) {
		Sys record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysVO.class);
	}

	public int updateById(SysQO qo) {
		Sys record = BeanUtil.copyProperties(qo, Sys.class);
		return dao.updateById(record);
	}

	public SysVO getSys() {
		return BeanUtil.copyProperties(dao.getSys(), SysVO.class);
	}

}
