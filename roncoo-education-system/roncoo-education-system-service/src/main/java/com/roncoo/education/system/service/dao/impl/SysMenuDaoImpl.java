package com.roncoo.education.system.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.system.service.dao.SysMenuDao;
import com.roncoo.education.system.service.dao.impl.mapper.SysMenuMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class SysMenuDaoImpl implements SysMenuDao {
	@Autowired
	private SysMenuMapper sysMenuMapper;

	public int save(SysMenu record) {
		record.setId(IdWorker.getId());
		return this.sysMenuMapper.insertSelective(record);
	}

	public int deleteById(Long id) {
		return this.sysMenuMapper.deleteByPrimaryKey(id);
	}

	public int updateById(SysMenu record) {
		return this.sysMenuMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByExampleSelective(SysMenu record, SysMenuExample example) {
		return this.sysMenuMapper.updateByExampleSelective(record, example);
	}

	public SysMenu getById(Long id) {
		return this.sysMenuMapper.selectByPrimaryKey(id);
	}

	public Page<SysMenu> listForPage(int pageCurrent, int pageSize, SysMenuExample example) {
		int count = this.sysMenuMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<SysMenu>(count, totalPage, pageCurrent, pageSize, this.sysMenuMapper.selectByExample(example));
	}

	@Override
	public List<SysMenu> listByParentId(Long parentId) {
		SysMenuExample example = new SysMenuExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		example.setOrderByClause(" sort desc, id desc");
		return this.sysMenuMapper.selectByExample(example);
	}

	@Override
	public List<SysMenu> listByParentIdAndNotMenuType(Long parentId, Integer menuType) {
		SysMenuExample example = new SysMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		if (menuType != null) {
			criteria.andMenuTypeNotEqualTo(menuType);
		}
		example.setOrderByClause(" sort desc, id desc");
		return this.sysMenuMapper.selectByExample(example);
	}

	@Override
	public List<SysMenu> listAll() {
		SysMenuExample example = new SysMenuExample();
		example.setOrderByClause(" sort desc, id desc");
		return this.sysMenuMapper.selectByExample(example);
	}

	@Override
	public List<SysMenu> listByMenuName(String menuName) {
		SysMenuExample example = new SysMenuExample();
		example.createCriteria().andMenuNameLike(menuName);
		example.setOrderByClause(" sort desc, id desc");
		return this.sysMenuMapper.selectByExample(example);
	}
}