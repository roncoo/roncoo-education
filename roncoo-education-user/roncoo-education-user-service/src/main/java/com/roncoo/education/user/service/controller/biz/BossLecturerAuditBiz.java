package com.roncoo.education.user.service.controller.biz;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.education.user.common.bean.qo.LecturerAuditQO;
import com.roncoo.education.user.common.bean.vo.LecturerAuditVO;
import com.roncoo.education.user.common.bean.vo.LecturerExtVO;
import com.roncoo.education.user.service.dao.LecturerAuditDao;
import com.roncoo.education.user.service.dao.LecturerDao;
import com.roncoo.education.user.service.dao.LecturerExtDao;
import com.roncoo.education.user.service.dao.UserDao;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerAudit;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerAuditExample;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerAuditExample.Criteria;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.UserTypeEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.NOUtil;
import com.roncoo.education.util.tools.SignUtil;
import com.roncoo.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@Component
public class BossLecturerAuditBiz extends BaseBiz {

	@Autowired
	private LecturerAuditDao dao;
	@Autowired
	private LecturerDao lecturerDao;
	@Autowired
	private LecturerExtDao lecturerExtDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserExtDao userExtDao;

	public Page<LecturerAuditVO> listForPage(LecturerAuditQO qo) {
		LecturerAuditExample example = new LecturerAuditExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNotEmpty(qo.getLecturerMobile())) {
			c.andLecturerMobileEqualTo(qo.getLecturerMobile());
		}
		if (StringUtils.isNotEmpty(qo.getLecturerName())) {
			c.andLecturerNameLike(PageUtil.rightLike(qo.getLecturerName()));
		}
		if (qo.getAuditStatus() != null) {
			c.andAuditStatusEqualTo(qo.getAuditStatus());
		}else {
			c.andAuditStatusNotEqualTo(AuditStatusEnum.SUCCESS.getCode());
		}
		if (qo.getStatusId() != null) {
			c.andStatusIdEqualTo(qo.getStatusId());
		}
		example.setOrderByClause(" audit_status asc, status_id desc, sort desc, id desc ");
		Page<LecturerAudit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, LecturerAuditVO.class);
	}

	@Transactional
	public int save(LecturerAuditQO qo) {
		// 手机号去空处理
		String mobile = qo.getLecturerMobile().trim();
		// 手机号码校验
		if (!Pattern.compile(REGEX_MOBILE).matcher(mobile).matches()) {
			throw new BaseException("手机号码格式不正确");
		}
		// 根据传入手机号获取用户信息(讲师的用户信息)
		UserExt userExt = userExtDao.getByMobile(mobile);
		// 1、用户不存在，注册用户
		if (ObjectUtil.isNull(userExt)) {
			if (StringUtils.isEmpty(qo.getMobilePsw())) {
				throw new BaseException("密码不能为空");
			}
			if (!qo.getConfirmPasswd().equals(qo.getMobilePsw())) {
				throw new BaseException("两次密码不一致，请重试！");
			}
			// 注册用户
			userExt = register(qo, mobile);
		}

		// 2、讲师不存在，添加讲师
		LecturerAudit lecturerAudit = dao.getByLecturerUserNo(userExt.getUserNo());
		if (ObjectUtil.isNull(lecturerAudit)) {

			return lecturerInfo(qo, userExt);

		} else {
			// 讲师存在，更新讲师审核表为待审核状态
			lecturerAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			return dao.updateById(lecturerAudit);
		}
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public LecturerAuditVO getById(Long id) {
		LecturerAudit record = dao.getById(id);
		LecturerAuditVO vo = BeanUtil.copyProperties(record, LecturerAuditVO.class);
		// 查找讲师账户信息
		LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(vo.getLecturerUserNo());
		vo.setLecturerExtVO(BeanUtil.copyProperties(lecturerExt, LecturerExtVO.class));
		return vo;
	}

	public int updateById(LecturerAuditQO qo) {
		LecturerAudit record = BeanUtil.copyProperties(qo, LecturerAudit.class);
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		return dao.updateById(record);
	}

	/**
	 * 讲师审核
	 * 
	 * @param qo
	 * @return
	 * @author wuyun
	 */
	@Transactional
	public int audit(LecturerAuditQO qo) {
		LecturerAudit lecturerAudit = dao.getById(qo.getId());

		if (AuditStatusEnum.SUCCESS.getCode().equals(qo.getAuditStatus())) {
			// 查找讲师信息表，是否存在该讲师
			Lecturer lecturer = lecturerDao.getByLecturerUserNo(lecturerAudit.getLecturerUserNo());
			if (ObjectUtil.isNull(lecturer)) {
				// 插入
				lecturer = BeanUtil.copyProperties(lecturerAudit, Lecturer.class);
				lecturer.setGmtCreate(null);
				lecturer.setGmtModified(null);
				lecturerDao.save(lecturer);
			} else {
				// 更新
				lecturer = BeanUtil.copyProperties(lecturerAudit, Lecturer.class);
				lecturer.setGmtCreate(null);
				lecturer.setGmtModified(null);
				lecturerDao.updateById(lecturer);
			}
			// 查找用户信息是否存在
			UserExt userExt = userExtDao.getByUserNo(lecturer.getLecturerUserNo());
			if (ObjectUtil.isNull(userExt)) {
				throw new BaseException("获取不到用户信息");
			}
			// 存在更新为讲师类型
			userExt.setUserType(UserTypeEnum.LECTURER.getCode());
			userExtDao.updateById(userExt);
		}

		LecturerAudit record = BeanUtil.copyProperties(qo, LecturerAudit.class);
		return dao.updateById(record);
	}

	/**
	 * 手机号码校验用户信息表是否存在
	 * 
	 * @param qo
	 * @return
	 * @author wuyun
	 */
	public LecturerAuditVO checkUserAndLecturer(LecturerAuditQO qo) {
		// 手机号码校验
		if (!Pattern.compile(REGEX_MOBILE).matcher(qo.getLecturerMobile()).matches()) {
			throw new BaseException("手机号码格式不正确");
		}
		// 根据传入手机号获取用户信息(讲师的用户信息)
		UserExt userExt = userExtDao.getByMobile(qo.getLecturerMobile());
		LecturerAuditVO vo = new LecturerAuditVO();

		if (ObjectUtil.isNull(userExt)) {
			// 用户不存在
			vo.setCheckUserAndLecturer(1);
		} else {
			// 根据用户编号获取讲师信息
			LecturerAudit record = dao.getByLecturerUserNo(userExt.getUserNo());
			if (ObjectUtil.isNull(record)) {
				vo.setCheckUserAndLecturer(2);
			} else {
				vo.setCheckUserAndLecturer(3);
			}
		}
		return vo;
	}

	/**
	 * 添加用户信息
	 * 
	 * @author wuyun
	 */
	private UserExt register(LecturerAuditQO lecturerAudit, String mobile) {
		// 用户基本信息
		User user = new User();
		user.setUserNo(NOUtil.getUserNo());
		user.setMobile(mobile);
		user.setMobileSalt(StrUtil.get32UUID());
		user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + lecturerAudit.getMobilePsw()));
		userDao.save(user);

		// 用户教育信息
		UserExt userExt = new UserExt();
		userExt.setUserNo(user.getUserNo());
		userExt.setMobile(user.getMobile());
		userExt.setNickname(lecturerAudit.getLecturerName());
		userExtDao.save(userExt);
		return userExt;
	}

	/**
	 * 添加讲师信息
	 * 
	 * @author wuyun
	 */
	private int lecturerInfo(LecturerAuditQO auditBO, UserExt userExt) {
		// 插入讲师信息
		LecturerAudit infoAudit = BeanUtil.copyProperties(auditBO, LecturerAudit.class);
		if (!StringUtils.isEmpty(userExt.getHeadImgUrl())) {
			infoAudit.setHeadImgUrl(userExt.getHeadImgUrl());
		}
		infoAudit.setLecturerUserNo(userExt.getUserNo());
		infoAudit.setLecturerProportion(LECTURER_DEFAULT_PROPORTION);// 设置讲师默认分成百分之70
		int infoAuditNum = dao.save(infoAudit);
		if (infoAuditNum < 1) {
			throw new BaseException("讲师信息表新增失败");
		}

		// 插入讲师账户
		LecturerExt lecturerExt = new LecturerExt();
		lecturerExt.setLecturerUserNo(infoAudit.getLecturerUserNo());
		lecturerExt.setTotalIncome(BigDecimal.ZERO);
		lecturerExt.setHistoryMoney(BigDecimal.ZERO);
		lecturerExt.setEnableBalances(BigDecimal.ZERO);
		lecturerExt.setFreezeBalances(BigDecimal.ZERO);
		lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
		int lecturerExtNum = lecturerExtDao.save(lecturerExt);
		if (lecturerExtNum < 1) {
			throw new BaseException("讲师账户表新增失败");
		}
		return lecturerExtNum;
	}
}
