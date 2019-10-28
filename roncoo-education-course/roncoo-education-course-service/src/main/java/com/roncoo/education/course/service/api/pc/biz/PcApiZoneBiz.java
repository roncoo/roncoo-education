package com.roncoo.education.course.service.api.pc.biz;

import com.roncoo.education.course.common.req.*;
import com.roncoo.education.course.common.resq.ZonePageRESQ;
import com.roncoo.education.course.common.resq.ZoneViewRESQ;
import com.roncoo.education.course.service.dao.ZoneDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * 专区
 *
 */
@Component
public class PcApiZoneBiz {

	@Autowired
	private ZoneDao dao;

	/**
	 * 分页列出
	 *
	 * @param req
	 * @return
	 */
	public Result<Page<ZonePageRESQ>> listForPage(ZonePageREQ req) {
		ZoneExample example = new ZoneExample();
		Criteria c = example.createCriteria();
		if (req.getZoneLocation() != null) {
			c.andZoneLocationEqualTo(req.getZoneLocation());
		}
		if (StringUtils.hasText(req.getZoneName())) {
			c.andZoneNameEqualTo(req.getZoneName());
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Zone> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, ZonePageRESQ.class));
	}

	public Result<Integer> save(ZoneSaveREQ req) {
		if (StringUtils.isEmpty(req.getZoneName())) {
			return Result.error("专区名称不能为空");
		}
		Zone record = BeanUtil.copyProperties(req, Zone.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	public Result<Integer> delete(ZoneDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<Integer> update(ZoneUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Zone record = BeanUtil.copyProperties(req, Zone.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<ZoneViewRESQ> view(ZoneViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Zone record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到专区信息");
		}
		return Result.success(BeanUtil.copyProperties(record, ZoneViewRESQ.class));
	}

}
