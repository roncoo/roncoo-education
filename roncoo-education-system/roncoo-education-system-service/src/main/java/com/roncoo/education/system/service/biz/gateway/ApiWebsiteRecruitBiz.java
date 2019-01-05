package com.roncoo.education.system.service.biz.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.bo.WebsiteRecruitBO;
import com.roncoo.education.system.common.bean.dto.WebsiteRecruitDTO;
import com.roncoo.education.system.service.dao.WebsiteRecruitDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteRecruit;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 招募中心
 *
 * @author YZJ
 */
@Component
public class ApiWebsiteRecruitBiz {

	@Autowired
	private WebsiteRecruitDao dao;

	public Result<WebsiteRecruitDTO> get(WebsiteRecruitBO websiteRecruitBO) {
		if (null == websiteRecruitBO.getRecruitType()) {
			return Result.error("recruitType不能为空");
		}
		// 根据招募分类获取招募信息
		WebsiteRecruit websiteRecruit = dao.getByRecruitType(websiteRecruitBO.getRecruitType());
		return Result.success(BeanUtil.copyProperties(websiteRecruit, WebsiteRecruitDTO.class));
	}

}
