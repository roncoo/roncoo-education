package com.roncoo.education.system.service.api.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.dto.WebsiteLinkDTO;
import com.roncoo.education.system.common.dto.WebsiteLinkListDTO;
import com.roncoo.education.system.service.dao.WebsiteLinkDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.WebsiteLink;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.StatusIdEnum;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteLinkBiz {

	@Autowired
	private WebsiteLinkDao dao;

	public Result<WebsiteLinkListDTO> list() {
		List<WebsiteLink> websiteLinkList = dao.listByStatusId(StatusIdEnum.YES.getCode());
		WebsiteLinkListDTO dto = new WebsiteLinkListDTO();
		dto.setWebsiteLinkList(PageUtil.copyList(websiteLinkList, WebsiteLinkDTO.class));
		return Result.success(dto);
	}

}
