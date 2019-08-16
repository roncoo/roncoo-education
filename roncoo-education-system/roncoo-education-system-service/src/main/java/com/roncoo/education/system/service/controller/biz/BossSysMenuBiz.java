package com.roncoo.education.system.service.controller.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysMenuQO;
import com.roncoo.education.system.common.bean.vo.SysMenuVO;
import com.roncoo.education.system.service.dao.SysMenuDao;
import com.roncoo.education.system.service.dao.SysMenuRoleDao;
import com.roncoo.education.system.service.dao.SysRoleUserDao;
import com.roncoo.education.system.service.dao.SysUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample.Criteria;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Component
public class BossSysMenuBiz {

	@Autowired
	private SysMenuDao dao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysRoleUserDao sysRoleUserDao;
	@Autowired
	private SysMenuRoleDao sysMenuRoleDao;

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

	public List<SysMenuVO> listByUserAndMenu(Long userNo) {
		List<SysMenuVO> list = new ArrayList<>();
		if (userNo == null) {
			// 没权限
			return list;
		}
		SysUser sysUser = sysUserDao.getByUserNo(userNo);
		if (ObjectUtil.isNull(sysUser)) {
			// 没权限
			return list;
		}
		List<SysMenuRole> sysMenuRoleList = new ArrayList<>();
		List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
		for (SysRoleUser sru : sysRoleUserList) {
			sysMenuRoleList.addAll(sysMenuRoleDao.listByRoleId(sru.getRoleId()));
		}
		// 筛选
		list = listByRole(sysMenuRoleList);
		return list;
	}

	private List<SysMenuVO> listByRole(List<SysMenuRole> sysMenuRoleList) {
		List<SysMenuVO> list = userRecursion(0L);
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

	/**
	 * 用户递归显示菜单(角色关联菜单)
	 */
	private List<SysMenuVO> userRecursion(Long parentId) {
		List<SysMenuVO> lists = new ArrayList<>();
		List<SysMenu> list = dao.listByParentId(parentId);
		if (CollectionUtil.isNotEmpty(list)) {
			for (SysMenu m : list) {
				SysMenuVO resq = BeanUtil.copyProperties(m, SysMenuVO.class);
				resq.setList(userRecursion(m.getId()));
				lists.add(resq);
			}
		}
		return lists;
	}

}
