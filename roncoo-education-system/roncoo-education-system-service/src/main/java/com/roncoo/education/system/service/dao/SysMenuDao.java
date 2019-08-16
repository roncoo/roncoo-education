package com.roncoo.education.system.service.dao;

import java.util.List;

import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.util.base.Page;

public interface SysMenuDao {
	int save(SysMenu record);

	int deleteById(Long id);

	int updateById(SysMenu record);

	int updateByExampleSelective(SysMenu record, SysMenuExample example);

	SysMenu getById(Long id);

	Page<SysMenu> listForPage(int pageCurrent, int pageSize, SysMenuExample example);

	/**
	 * 根据父ID获取菜单
	 * 
	 * @param parentId
	 * @return
	 */
	List<SysMenu> listByParentId(Long parentId);

	/**
	 * 根据父ID、如果菜单类型为空获取所有菜单，包含按钮,则获取所有菜单，不包含按钮
	 * 
	 * @param parentId
	 * @param menuType
	 * @return
	 */
	List<SysMenu> listByParentIdAndNotMenuType(Long parentId, Integer menuType);

	/**
	 * 列出所有菜单
	 * 
	 * @return
	 */
	List<SysMenu> listAll();

	/**
	 * 根据菜单名称列出菜单信息(模糊查询)
	 * 
	 * @param menuName
	 * @return
	 */
	List<SysMenu> listByMenuName(String menuName);
}