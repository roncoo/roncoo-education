package com.roncoo.education.system.service.api.pc.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.roncoo.education.system.common.req.SysUserDeleteREQ;
import com.roncoo.education.system.common.req.SysUserPageREQ;
import com.roncoo.education.system.common.req.SysUserSaveREQ;
import com.roncoo.education.system.common.req.SysUserUpdatePasswordREQ;
import com.roncoo.education.system.common.req.SysUserUpdateREQ;
import com.roncoo.education.system.common.req.SysUserViewREQ;
import com.roncoo.education.system.common.resq.SysUserPageRESQ;
import com.roncoo.education.system.common.resq.SysUserViewRESQ;
import com.roncoo.education.system.service.dao.SysRoleUserDao;
import com.roncoo.education.system.service.dao.SysUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUserExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.roncoo.education.user.feign.qo.UserQO;
import com.roncoo.education.user.feign.vo.UserVO;
import com.roncoo.education.user.feign.interfaces.IFeignUser;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Component
public class PcApiSysUserBiz {

	@Autowired
	private IFeignUser bossUser;

	@Autowired
	private SysRoleUserDao sysRoleUserDao;
	@Autowired
	private SysUserDao dao;

	public Result<Page<SysUserPageRESQ>> list(SysUserPageREQ req) {
		SysUserExample example = new SysUserExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getMobile())) {
			c.andMobileEqualTo(req.getMobile());
		}
		example.setOrderByClause(" sort asc, status_id desc, id desc ");
		Page<SysUser> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, SysUserPageRESQ.class));
	}

	public Result<Integer> save(SysUserSaveREQ req) {
		if (req.getAdminUserNo()== null) {
			return Result.error("userNo不能为空");
		}
		UserVO userVO = bossUser.getByUserNo(req.getAdminUserNo());
		if (ObjectUtil.isNull(userVO)) {
			throw new BaseException("找不到用户信息,请重试");
		}
		SysUser sysUser = dao.getByUserNo(req.getAdminUserNo());
		if (ObjectUtil.isNotNull(sysUser)) {
			return Result.error("用户已添加成管理员");
		}
		SysUser record = BeanUtil.copyProperties(req, SysUser.class);
		record.setUserNo(req.getAdminUserNo());
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	@Transactional
	public Result<Integer> delete(SysUserDeleteREQ req) {
		if (req.getId() == null) {
			return Result.error("主键ID不能为空");
		}
		// 1、删除用户所有角色
		sysRoleUserDao.deleteByUserId(req.getId());
		// 2、删除用户
		return Result.success(dao.deleteById(req.getId()));
	}

	public Result<Integer> update(SysUserUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("主键ID不能为空");
		}
		SysUser sysUser = dao.getById(req.getId());
		if (ObjectUtil.isNull(sysUser)) {
			return Result.error("找不到管理员信息");
		}
		SysUser record = BeanUtil.copyProperties(req, SysUser.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
	}

	public Result<SysUserViewRESQ> view(SysUserViewREQ req) {
		if (req.getId() == null) {
			return Result.error("主键ID不能为空");
		}
		SysUser sysUser = dao.getById(req.getId());
		if (ObjectUtil.isNull(sysUser)) {
			return Result.error("管理员不存在");
		}
		return Result.success(BeanUtil.copyProperties(sysUser, SysUserViewRESQ.class));
	}

	public Result<Integer> updatePassword(SysUserUpdatePasswordREQ req) {
		if (req.getAdminUserNo() == null) {
			return Result.error("用户编号不能为空,请重试");
		}
		if (StringUtils.isEmpty(req.getMobilePsw())) {
			return Result.error("新密码不能为空,请重试");
		}
		if (StringUtils.isEmpty(req.getRePwd())) {
			return Result.error("确认密码不能为空,请重试");
		}
		if (!req.getRePwd().equals(req.getMobilePsw())) {
			return Result.error("密码不一致,请重试");
		}
		UserVO userVO = bossUser.getByUserNo(req.getAdminUserNo());
		if (ObjectUtil.isNull(userVO)) {
			return Result.error("找不到用户信息,请重试");
		}
		if (DigestUtil.sha1Hex(userVO.getMobileSalt() + req.getMobilePsw()).equals(userVO.getMobilePsw())) {
			return Result.error("输入的密码与原密码一致,请重试");
		}
		UserQO userQO = new UserQO();
		userQO.setId(userVO.getId());
		userQO.setMobilePsw(DigestUtil.sha1Hex(userVO.getMobileSalt() + req.getMobilePsw()));
		int results = bossUser.updateById(userQO);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
	}

}
