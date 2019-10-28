package com.roncoo.education.user.service.feign.biz;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.education.user.feign.qo.LecturerProfitQO;
import com.roncoo.education.user.feign.vo.LecturerProfitVO;
import com.roncoo.education.user.feign.vo.LecturerVO;
import com.roncoo.education.user.service.dao.LecturerDao;
import com.roncoo.education.user.service.dao.LecturerExtDao;
import com.roncoo.education.user.service.dao.LecturerProfitDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerProfit;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerProfitExample;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerProfitExample.Criteria;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.ProfitStatusEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.util.tools.SignUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
@Component
public class FeignLecturerProfitBiz extends BaseBiz {

	@Autowired
	private LecturerProfitDao dao;
	@Autowired
	private LecturerDao lecturerDao;
	@Autowired
	private LecturerExtDao lecturerExtDao;

	public Page<LecturerProfitVO> listForPage(LecturerProfitQO qo) {
		LecturerProfitExample example = new LecturerProfitExample();
		Criteria c = example.createCriteria();
		// 将页面的讲师名称为条件查询出对应的讲师ID，再将讲师ID作为查询条件
		if (StringUtils.isNotEmpty(qo.getLecturerName())) {
			List<Lecturer> lecturerList = lecturerDao.getByLecturerName(qo.getLecturerName());
			if (ObjectUtil.isNull(lecturerList)) {
				c.andLecturerUserNoEqualTo(0L);
			} else {
				for (Lecturer lecturer : lecturerList) {
					c.andLecturerUserNoEqualTo(lecturer.getLecturerUserNo());
				}
			}
		}
		if (qo.getProfitStatus() != null) {
			c.andProfitStatusEqualTo(qo.getProfitStatus());
		}
		if (StringUtils.isNotEmpty(qo.getBeginDate())) {
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginDate(), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotEmpty(qo.getEndDate())) {
			c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndDate(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" profit_status asc , id desc ");
		Page<LecturerProfit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		Page<LecturerProfitVO> pageVO = PageUtil.transform(page, LecturerProfitVO.class);
		// 讲师信息
		if (!pageVO.getList().isEmpty()) {
			for (LecturerProfitVO vo : pageVO.getList()) {
				Lecturer lecturer = lecturerDao.getByLecturerUserNo(vo.getLecturerUserNo());
				vo.setLecturerVO(BeanUtil.copyProperties(lecturer, LecturerVO.class));
			}
		}
		return pageVO;
	}

	public int save(LecturerProfitQO qo) {
		LecturerProfit record = BeanUtil.copyProperties(qo, LecturerProfit.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public LecturerProfitVO getById(Long id) {
		LecturerProfit record = dao.getById(id);
		return BeanUtil.copyProperties(record, LecturerProfitVO.class);
	}

	@Transactional
	public int updateById(LecturerProfitQO qo) {

		LecturerProfit lecturerProfit = dao.getById(qo.getId());
		if (ObjectUtil.isNull(lecturerProfit)) {
			throw new BaseException("讲师提现日志信息不存在");
		}

		// 审核成功时进来
		if (ProfitStatusEnum.SUCCESS.getCode().equals(qo.getProfitStatus())) {
			// 根据传入讲师用户编号获取讲师账户信息
			LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(lecturerProfit.getLecturerUserNo());
			if (ObjectUtil.isNull(lecturerExt)) {
				throw new BaseException("讲师账户信息不存在");
			}

			String sign = SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances());
			if (!sign.equals(lecturerExt.getSign())) {
				logger.error("签名为：{}，{}", sign, lecturerExt.getSign());
			}

			lecturerExt.setHistoryMoney(lecturerExt.getHistoryMoney().add(lecturerExt.getFreezeBalances()));// 已提金额 = 原来的已提金额 + 冻结金额
			lecturerExt.setFreezeBalances(BigDecimal.ZERO);
			lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
			int lecturerExtNum = lecturerExtDao.updateById(lecturerExt);
			if (lecturerExtNum < 1) {
				throw new BaseException("讲师账户信息更新失败");
			}
		}

		LecturerProfit record = BeanUtil.copyProperties(qo, LecturerProfit.class);
		return dao.updateById(record);
	}

}
