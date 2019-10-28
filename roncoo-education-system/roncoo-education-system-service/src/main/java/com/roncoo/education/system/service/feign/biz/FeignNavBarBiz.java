package com.roncoo.education.system.service.feign.biz;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.feign.qo.NavBarQO;
import com.roncoo.education.system.feign.vo.NavBarVO;
import com.roncoo.education.system.service.dao.NavBarDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.NavBar;
import com.roncoo.education.system.service.dao.impl.mapper.entity.NavBarExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.NavBarExample.Criteria;
import com.roncoo.education.util.base.Base;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Component
public class FeignNavBarBiz {

	@Autowired
	private NavBarDao dao;

	public Page<NavBarVO> listForPage(NavBarQO qo) {
		NavBarExample example = new NavBarExample();
		Criteria c = example.createCriteria();
		if (qo.getStatusId() != null) {
			c.andStatusIdEqualTo(qo.getStatusId());
		} else {
			c.andStatusIdLessThan(Base.FREEZE);
		}
		if (StringUtils.isNotEmpty(qo.getNavTitle())) {
			c.andNavTitleLike(PageUtil.rightLike(qo.getNavTitle()));
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc  ");
		Page<NavBar> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, NavBarVO.class);
	}

	public int save(NavBarQO qo) {
		if (StringUtils.isEmpty(qo.getNavUrl())) {
			throw new BaseException("导航链接不能为空");
		}
		if (StringUtils.isEmpty(qo.getTarget())) {
			throw new BaseException("跳转方式不能为空");
		}
		NavBar navBar = dao.getByNavUrl(qo.getNavUrl());
		if (ObjectUtil.isNotNull(navBar)) {
			throw new BaseException("已经添加该导航");
		}
		NavBar record = BeanUtil.copyProperties(qo, NavBar.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public NavBarVO getById(Long id) {
		NavBar record = dao.getById(id);
		return BeanUtil.copyProperties(record, NavBarVO.class);
	}

	public int updateById(NavBarQO qo) {
		if (qo.getStatusId() == null && StringUtils.isEmpty(qo.getTarget())) {
			throw new BaseException("跳转方式不能为空");
		}
		NavBar record = BeanUtil.copyProperties(qo, NavBar.class);
		return dao.updateById(record);
	}

}
