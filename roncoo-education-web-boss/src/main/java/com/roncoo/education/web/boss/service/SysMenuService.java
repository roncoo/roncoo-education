/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.web.boss.common.bean.qo.SysMenuQO;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuRoleVO;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuVO;
import com.roncoo.education.web.boss.service.dao.SysMenuDao;
import com.roncoo.education.web.boss.service.dao.SysMenuRoleDao;
import com.roncoo.education.web.boss.service.dao.SysRoleUserDao;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 菜单信息
 */
@Component
public class SysMenuService {

	@Autowired
	private SysMenuDao dao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysMenuRoleDao sysMenuRoleDao;
	@Autowired
	private SysRoleUserDao sysRoleUserDao;

	public List<SysMenuVO> list(SysMenuQO qo) {
		List<SysMenu> list = dao.listAll();
		return PageUtil.copyList(list, SysMenuVO.class);
	}

	public Long save(SysMenuQO qo) {
		SysMenu record = BeanUtil.copyProperties(qo, SysMenu.class);
		return dao.save(record);
	}

	@Transactional
	public int deleteById(Long id) {
		// 删除菜单,存在子菜单则迭代删除子菜单
		List<SysMenu> list = dao.listByParentId(id);
		if (CollectionUtil.isNotEmpty(list)) {
			for (SysMenu sysMenu : list) {
				deleteById(sysMenu.getId());
			}
		}
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

	public List<SysMenuVO> listMenuForRole(List<SysMenuRoleVO> sysMenuRoleVolist) {
		List<SysMenuVO> sysMenuVoList = recursion(0L);
		checkMenu(sysMenuVoList, sysMenuRoleVolist);
		return sysMenuVoList;
	}

	/**
	 * 递归显示菜单(角色关联菜单)
	 */
	private List<SysMenuVO> recursion(Long parentId) {
		List<SysMenuVO> lists = new ArrayList<>();
		List<SysMenu> list = dao.listByParentId(parentId);
		if (CollectionUtil.isNotEmpty(list)) {
			for (SysMenu m : list) {
				SysMenuVO vo = BeanUtil.copyProperties(m, SysMenuVO.class);
				vo.setList(recursion(m.getId()));
				lists.add(vo);
			}
		}
		return lists;
	}

	private List<SysMenuVO> checkMenu(List<SysMenuVO> sysMenuVoList, List<SysMenuRoleVO> sysMenuRoleVolist) {
		for (SysMenuVO mv : sysMenuVoList) {
			Integer isShow = 0;
			for (SysMenuRoleVO vo : sysMenuRoleVolist) {
				if (mv.getId().equals(vo.getMenuId())) {
					isShow = 1;
					break;
				}
			}
			mv.setIsShow(isShow);
			checkMenu(mv.getList(), sysMenuRoleVolist);
		}
		return sysMenuVoList;
	}

	public List<SysMenuVO> listByUser(Long userNo) {
		// 用户-->角色-->菜单
		SysUser sysUser = sysUserDao.getByUserNo(userNo);
		if (ObjectUtil.isNull(sysUser)) {
			throw new BaseException("用户异常");
		}

		List<SysMenuRole> sysMenuRoleList = new ArrayList<>();
		List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
		for (SysRoleUser sru : sysRoleUserList) {
			sysMenuRoleList.addAll(sysMenuRoleDao.listByRoleId(sru.getRoleId()));
		}

		// 筛选
		return listByRole(sysMenuRoleList);
	}

	private List<SysMenuVO> listByRole(List<SysMenuRole> sysMenuRoleList) {
		List<SysMenuVO> list = recursion(0L);
		List<SysMenuVO> sysMenuVOList = new ArrayList<>();
		sysMenuVOList = listMenu(sysMenuVOList, sysMenuRoleList, list);
		return sysMenuVOList;
	}

	private List<SysMenuVO> listMenu(List<SysMenuVO> sysMenuVOList, List<SysMenuRole> sysMenuRoleList, List<SysMenuVO> list) {
		for (SysMenuVO mv : list) {
			SysMenuVO v = null;
			for (SysMenuRole vo : sysMenuRoleList) {
				if (mv.getId().equals(vo.getMenuId())) {
					v = BeanUtil.copyProperties(mv, SysMenuVO.class);
					break;
				}
			}
			if (ObjectUtil.isNotNull(v)) {
				sysMenuVOList.add(v);
				List<SysMenuVO> l = new ArrayList<>();
				if (v != null) {
					v.setList(l);
				}
				listMenu(l, sysMenuRoleList, mv.getList());
			}
		}
		return sysMenuVOList;
	}
}
