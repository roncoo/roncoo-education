/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.web.boss.common.bean.qo.SysRoleUserQO;
import com.roncoo.education.web.boss.common.bean.vo.SysRoleUserVO;
import com.roncoo.education.web.boss.service.dao.SysRoleUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUserExample;

/**
 * 角色用户关联表
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Component
public class SysRoleUserService {

	@Autowired
	private SysRoleUserDao dao;

	public Page<SysRoleUserVO> listForPage(SysRoleUserQO qo) {
		SysRoleUserExample example = new SysRoleUserExample();
		example.setOrderByClause(" id desc ");
		Page<SysRoleUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysRoleUserVO.class);
	}

	public int save(SysRoleUserQO qo) {
		SysRoleUser record = BeanUtil.copyProperties(qo, SysRoleUser.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysRoleUserVO getById(Long id) {
		SysRoleUser record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysRoleUserVO.class);
	}

	public int updateById(SysRoleUserQO qo) {
		SysRoleUser record = BeanUtil.copyProperties(qo, SysRoleUser.class);
		return dao.updateById(record);
	}

	public List<SysRoleUserVO> listByUserId(Long userId) {
		List<SysRoleUser> list = dao.listByUserId(userId);
		return PageUtil.copyList(list, SysRoleUserVO.class);
	}

	@Transactional
	public int save(SysRoleUserQO qo, String ids) {
		if (StringUtils.hasText(ids)) {
			// 先删除旧的，再添加新的
			dao.deleteByUserId(qo.getUserId());
			// 拆分角色和平台拼接ID
			String[] roleIds = ids.split(",");
			for (String roleId : roleIds) {
				SysRoleUser sysRoleUser = new SysRoleUser();
				sysRoleUser.setRoleId(Long.parseLong(roleId));
				sysRoleUser.setUserId(qo.getUserId());
				dao.save(sysRoleUser);
			}
		}
		return 1;
	}

}
