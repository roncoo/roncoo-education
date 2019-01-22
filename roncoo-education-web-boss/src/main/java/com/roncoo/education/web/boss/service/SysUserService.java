/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.roncoo.education.user.common.bean.qo.UserQO;
import com.roncoo.education.user.common.bean.vo.UserVO;
import com.roncoo.education.user.feign.IBossUser;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.web.boss.common.bean.qo.SysUserQO;
import com.roncoo.education.web.boss.common.bean.vo.SysUserVO;
import com.roncoo.education.web.boss.service.dao.SysRoleUserDao;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUserExample;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUserExample.Criteria;

/**
 * 后台用户信息
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Component
public class SysUserService {

	@Autowired
	private SysUserDao dao;

	@Autowired
	private SysRoleUserDao sysRoleUserDao;
	
	@Autowired
	private IBossUser bossUser;

	public Page<SysUserVO> listForPage(SysUserQO qo) {
		SysUserExample example = new SysUserExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(qo.getMobile())) {
			c.andMobileEqualTo(qo.getMobile());
		}
		example.setOrderByClause(" sort asc, status_id desc, id desc ");
		Page<SysUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysUserVO.class);
	}

	public int save(SysUserQO qo) {
		SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
		return dao.save(record);
	}

	@Transactional
	public int deleteById(Long id) {
		// 1、删除用户所有角色
		sysRoleUserDao.deleteByUserId(id);
		// 2、删除用户
		return dao.deleteById(id);
	}

	public SysUserVO getById(Long id) {
		SysUser record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysUserVO.class);
	}

	@Transactional
	public int updateById(SysUserQO qo) {
		SysUser sysUser = dao.getById(qo.getId());
		UserVO user = bossUser.getByMobile(sysUser.getMobile());
		if (user == null) {
			throw new BaseException("找不到用户信息");
		}
		UserQO userQO = new UserQO();
		userQO.setId(user.getId());
		userQO.setMobile(qo.getMobile());
		bossUser.updateById(userQO);
		SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
		return dao.updateById(record);
	}

	public SysUserVO getByUserNo(Long userNo) {
		SysUser record = dao.getByUserNo(userNo);
		return BeanUtil.copyProperties(record, SysUserVO.class);
	}

	public SysUserVO getByUserNoAndOrgNo(Long userNo, String orgNo) {
		SysUser record = dao.getByUserNo(userNo);
		if (record == null) {
			throw new BaseException("找不到用户信息");
		}
		return BeanUtil.copyProperties(record, SysUserVO.class);
	}

}
