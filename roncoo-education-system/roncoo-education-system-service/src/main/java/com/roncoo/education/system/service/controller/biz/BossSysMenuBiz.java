package com.roncoo.education.system.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysMenuQO;
import com.roncoo.education.system.common.bean.vo.SysMenuVO;
import com.roncoo.education.system.service.dao.SysMenuDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Component
public class BossSysMenuBiz {

	@Autowired
	private SysMenuDao dao;

	public Page<SysMenuVO> listForPage(SysMenuQO qo) {
		SysMenuExample example = new SysMenuExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" id desc ");
		Page<SysMenu> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysMenuVO.class);
	}

	public int save(SysMenuQO qo) {
		SysMenu record = BeanUtil.copyProperties(qo, SysMenu.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysMenuVO getById(Long id) {
		SysMenu record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysMenuVO.class);
	}

	public int updateById(SysMenuQO qo) {
		SysMenu record = BeanUtil.copyProperties(qo, SysMenu.class);
		return dao.updateById(record);
	}

}
