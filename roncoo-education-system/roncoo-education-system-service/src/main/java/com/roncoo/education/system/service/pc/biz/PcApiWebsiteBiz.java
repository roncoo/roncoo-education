package com.roncoo.education.system.service.pc.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.service.pc.req.WebsiteUpdateREQ;
import com.roncoo.education.system.service.pc.resq.WebsiteViewRESQ;
import com.roncoo.education.system.dao.WebsiteDao;
import com.roncoo.education.system.dao.impl.mapper.entity.Website;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 站点信息
 *
 */
@Component
public class PcApiWebsiteBiz {

	@Autowired
	private WebsiteDao dao;

	public Result<WebsiteViewRESQ> view() {
		Website website = dao.getWebsite();
		return Result.success(BeanUtil.copyProperties(website, WebsiteViewRESQ.class));
	}

	public Result<Integer> update(WebsiteUpdateREQ req) {
		Website record = BeanUtil.copyProperties(req, Website.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

}
