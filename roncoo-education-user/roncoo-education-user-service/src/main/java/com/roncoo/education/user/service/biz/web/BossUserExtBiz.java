package com.roncoo.education.user.service.biz.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.user.common.bean.qo.UserExtEchartsQO;
import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.user.common.bean.vo.UserEchartsVO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExtExample;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExtExample.Criteria;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Component
public class BossUserExtBiz extends BaseBiz {

	@Autowired
	private UserExtDao dao;

	public Page<UserExtVO> listForPage(UserExtQO qo) {
		UserExtExample example = new UserExtExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(qo.getMobile())) {
			c.andMobileLike(PageUtil.rightLike(qo.getMobile()));
		}
		if (!StringUtils.isEmpty(qo.getUserNo())) {
			c.andUserNoEqualTo(qo.getUserNo());
		}
		if (StringUtils.hasText(qo.getBeginGmtCreate())) {
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginGmtCreate(), "yyyy-MM-dd"));
		}
		if (StringUtils.hasText(qo.getEndGmtCreate())) {
			c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndGmtCreate(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" status_id desc, id desc ");
		Page<UserExt> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, UserExtVO.class);
	}

	public int save(UserExtQO qo) {
		UserExt record = BeanUtil.copyProperties(qo, UserExt.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public UserExtVO getById(Long id) {
		UserExt record = dao.getById(id);
		return BeanUtil.copyProperties(record, UserExtVO.class);
	}

	public int updateById(UserExtQO qo) {
		UserExt record = BeanUtil.copyProperties(qo, UserExt.class);
		return dao.updateById(record);
	}

	public UserExtVO getByUserNo(Long userNo) {
		UserExt record = dao.getByUserNo(userNo);
		return BeanUtil.copyProperties(record, UserExtVO.class);
	}

	/**
	 * 获取用户注册量
	 * 
	 * @param userExtEchartsQO
	 * @return
	 * @author wuyun
	 */
	public List<UserEchartsVO> sumByCounts(UserExtEchartsQO userExtEchartsQO) {
		List<UserEchartsVO> list = new ArrayList<>();
		List<Integer> countOrders = new ArrayList<>();
		UserEchartsVO vo = new UserEchartsVO();
		for (String date : userExtEchartsQO.getDateList()) {
			Integer sum = dao.sumByCountOrders(date);
			countOrders.add(sum);
		}
		vo.setCount(countOrders);
		list.add(vo);
		return list;
	}
}
