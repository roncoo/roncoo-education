/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.web.boss.common.bean.qo.SysMenuRoleQO;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuRoleVO;
import com.roncoo.education.web.boss.service.dao.SysMenuRoleDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenuRole;

/**
 * 菜单角色关联表
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Component
public class SysMenuRoleService {

	@Autowired
	private SysMenuRoleDao dao;

	public List<SysMenuRoleVO> listByRoleId(Long roleId) {
		List<SysMenuRole> list = dao.listByRoleId(roleId);
		return PageUtil.copyList(list, SysMenuRoleVO.class);
	}

	public int save(SysMenuRoleQO qo, String ids) {
		if (StringUtils.hasText(ids)) {
			dao.deleteByRoleId(qo.getRoleId());
			String[] menuIds = ids.split(",");
			for (String id : menuIds) {
				SysMenuRole entity = new SysMenuRole();
				entity.setMenuId(Long.valueOf(id));
				entity.setRoleId(qo.getRoleId());
				dao.save(entity);
			}
		}
		return 1;
	}

}
