package com.roncoo.education.system.service.api.pc.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.req.WebsiteNavArticleSaveREQ;
import com.roncoo.education.system.common.req.WebsiteNavArticleUpdateREQ;
import com.roncoo.education.system.common.req.WebsiteNavArticleViewREQ;
import com.roncoo.education.system.common.resq.WebsiteNavArticleViewRESQ;
import com.roncoo.education.system.service.dao.WebsiteNavArticleDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 站点导航文章
 *
 */
@Component
public class PcApiWebsiteNavArticleBiz {

	@Autowired
	private WebsiteNavArticleDao dao;

	public Result<WebsiteNavArticleViewRESQ> view(WebsiteNavArticleViewREQ req) {
		if (req.getNavId() == null) {
			return Result.error("NavId不能为空");
		}
		WebsiteNavArticle record = dao.getByNavId(req.getNavId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到导航文章信息");
		}
		return Result.success(BeanUtil.copyProperties(record, WebsiteNavArticleViewRESQ.class));
	}

	public Result<Integer> save(WebsiteNavArticleSaveREQ req) {
		if (req.getNavId() == null) {
			return Result.error("NavId不能为空");
		}
		WebsiteNavArticle record = BeanUtil.copyProperties(req, WebsiteNavArticle.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	public Result<Integer> update(WebsiteNavArticleUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("Id不能为空");
		}
		WebsiteNavArticle record = BeanUtil.copyProperties(req, WebsiteNavArticle.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

}
