package com.roncoo.education.course.service.biz.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.common.bean.bo.AdvBO;
import com.roncoo.education.course.common.bean.dto.AdvDTO;
import com.roncoo.education.course.common.bean.dto.AdvListDTO;
import com.roncoo.education.course.service.dao.AdvDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Adv;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 广告信息
 *
 * @author wujing
 */
@Component
public class ApiAdvBiz {

	@Autowired
	private AdvDao advDao;

	public Result<AdvListDTO> list(AdvBO bo) {
		AdvListDTO dto = new AdvListDTO();
		// 开始时间和结束时间
		List<Adv> advList = advDao.listByPlatShowAndStatusId(bo.getPlatShow(), StatusIdEnum.YES.getCode());
		if (CollectionUtil.isEmpty(advList)) {
			return Result.error("找不到广告信息");
		}
		dto.setAdvList(PageUtil.copyList(advList, AdvDTO.class));
		return Result.success(dto);
	}

}
