package com.roncoo.education.system.service.api.pc.biz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.education.system.common.req.WebsiteNavDeleteREQ;
import com.roncoo.education.system.common.req.WebsiteNavPageREQ;
import com.roncoo.education.system.common.req.WebsiteNavSaveREQ;
import com.roncoo.education.system.common.req.WebsiteNavUpdateREQ;
import com.roncoo.education.system.common.req.WebsiteNavViewREQ;
import com.roncoo.education.system.common.resq.WebsiteNavPageRESQ;
import com.roncoo.education.system.common.resq.WebsiteNavViewRESQ;
import com.roncoo.education.system.service.dao.WebsiteNavArticleDao;
import com.roncoo.education.system.service.dao.WebsiteNavDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavExample.Criteria;
import com.roncoo.education.util.base.Base;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 底部导航
 */
@Component
public class PcApiWebsiteNavBiz {

	@Autowired
	private WebsiteNavDao dao;
	@Autowired
	private WebsiteNavArticleDao websiteNavArticleDao;

	/**
	 * 底部导航分页列表接口
	 *
	 * @param websiteNavPageREQ
	 * @return
	 */
	public Result<Page<WebsiteNavPageRESQ>> list(WebsiteNavPageREQ req) {
		WebsiteNavExample example = new WebsiteNavExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNotEmpty(req.getNavName())) {
			c.andNavNameLike(PageUtil.like(req.getNavName()));
		} else {
			c.andParentIdEqualTo(Long.valueOf(0));
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		} else {
			c.andStatusIdLessThan(Base.FREEZE);
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<WebsiteNav> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<WebsiteNavPageRESQ> listForPage = PageUtil.transform(page, WebsiteNavPageRESQ.class);
		if (CollectionUtils.isNotEmpty(listForPage.getList())) {
			for (WebsiteNavPageRESQ resq : listForPage.getList()) {
				websiteNav(resq);
			}
		}
		return Result.success(listForPage);
	}

	private WebsiteNavPageRESQ websiteNav(WebsiteNavPageRESQ resq) {
		List<WebsiteNav> websiteNav = dao.listByParentId(resq.getId());
		if (ObjectUtil.isNotNull(websiteNav)) {
			List<WebsiteNavPageRESQ> websiteNavList = PageUtil.copyList(websiteNav, WebsiteNavPageRESQ.class);
			for (WebsiteNavPageRESQ websiteNavV2 : websiteNavList) {
				WebsiteNavArticle websiteNavArticle = websiteNavArticleDao.getByNavId(websiteNavV2.getId());
				if (ObjectUtil.isNotNull(websiteNavArticle)) {
					websiteNavV2.setIsArticle(1);
				} else {
					websiteNavV2.setIsArticle(0);
				}
			}
			resq.setChildren(websiteNavList);
		}
		return resq;

	}

	public Result<Integer> save(WebsiteNavSaveREQ req) {
		if (StringUtils.isEmpty(req.getNavName())) {
			return Result.error("ID不能为空");
		}
		if (req.getParentId() != null && !req.getParentId().equals(0L)) {
			WebsiteNav websiteNav = dao.getById(req.getParentId());
			if (ObjectUtil.isNull(websiteNav)) {
				return Result.error("找不到父级导航信息");
			}
		}
		WebsiteNav record = BeanUtil.copyProperties(req, WebsiteNav.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	public Result<Integer> delete(WebsiteNavDeleteREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		WebsiteNav websiteNav = dao.getById(req.getId());
		if (ObjectUtil.isNull(websiteNav)) {
			return Result.error("找不到底部导航信息");
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			WebsiteNavArticle websiteNavArticle = websiteNavArticleDao.getByNavId(req.getId());
			if (ObjectUtil.isNotNull(websiteNavArticle)) {
				int result = websiteNavArticleDao.deleteById(websiteNavArticle.getId());
				if (result < 1) {
					return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
				}
			}
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
	}

	@Transactional
	public Result<Integer> update(WebsiteNavUpdateREQ req) {
		WebsiteNav record = BeanUtil.copyProperties(req, WebsiteNav.class);
		int result = dao.updateById(record);
		// 父级状态改变，更新所有子级状态
		if (req.getStatusId() != null) {
			List<WebsiteNav> websiteNavList = dao.listByParentId(req.getId());
			if (ObjectUtil.isNotNull(websiteNavList)) {
				for (WebsiteNav websiteNav : websiteNavList) {
					websiteNav.setParentId(req.getId());
					websiteNav.setStatusId(req.getStatusId());
					dao.updateById(websiteNav);
				}
			}
		}
		return Result.success(result);
	}

	public Result<WebsiteNavViewRESQ> view(WebsiteNavViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		WebsiteNav websiteNav = dao.getById(req.getId());
		if (ObjectUtil.isNull(websiteNav)) {
			return Result.error("找不到底部导航信息");
		}
		return Result.success(BeanUtil.copyProperties(websiteNav, WebsiteNavViewRESQ.class));
	}

}
