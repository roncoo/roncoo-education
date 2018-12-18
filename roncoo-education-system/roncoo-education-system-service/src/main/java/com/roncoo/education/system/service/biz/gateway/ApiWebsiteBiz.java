package com.roncoo.education.system.service.biz.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.dto.WebsiteDTO;
import com.roncoo.education.system.service.dao.WebsiteDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Website;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteBiz {

	@Autowired
	private WebsiteDao websitedao;

	public Result<WebsiteDTO> get() {
		Website website = websitedao.getByStatusId(StatusIdEnum.YES.getCode());
		WebsiteDTO dto = BeanUtil.copyProperties(website, WebsiteDTO.class);
		return Result.success(dto);
	}

}
