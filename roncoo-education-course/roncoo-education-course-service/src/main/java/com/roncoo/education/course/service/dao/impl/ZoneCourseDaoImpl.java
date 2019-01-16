package com.roncoo.education.course.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.ZoneCourseDao;
import com.roncoo.education.course.service.dao.impl.mapper.ZoneCourseMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneCourseExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneCourseExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class ZoneCourseDaoImpl implements ZoneCourseDao {
	@Autowired
	private ZoneCourseMapper zoneCourseMapper;

	@Override
	public int save(ZoneCourse record) {
		record.setId(IdWorker.getId());
		return this.zoneCourseMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.zoneCourseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(ZoneCourse record) {
		return this.zoneCourseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ZoneCourse getById(Long id) {
		return this.zoneCourseMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<ZoneCourse> listForPage(int pageCurrent, int pageSize, ZoneCourseExample example) {
		int count = this.zoneCourseMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<ZoneCourse>(count, totalPage, pageCurrent, pageSize, this.zoneCourseMapper.selectByExample(example));
	}

	@Override
	public List<ZoneCourse> listByZoneIdAndStatusId(Long zoneId, Integer statusId) {
		ZoneCourseExample example = new ZoneCourseExample();
		Criteria c = example.createCriteria();
		c.andZoneIdEqualTo(zoneId);
		c.andStatusIdEqualTo(statusId);
		example.setOrderByClause("sort desc, id desc");
		return this.zoneCourseMapper.selectByExample(example);
	}

	@Override
	public List<ZoneCourse> listByZoneId(Long zoneId) {
		ZoneCourseExample example = new ZoneCourseExample();
		Criteria c = example.createCriteria();
		c.andZoneIdEqualTo(zoneId);
		c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
		return this.zoneCourseMapper.selectByExample(example);
	}

	@Override
	public ZoneCourse getZoneIdAndCourseId(Long courseId, Long zoneId) {
		ZoneCourseExample example = new ZoneCourseExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		c.andZoneIdEqualTo(zoneId);
		List<ZoneCourse> list = this.zoneCourseMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}