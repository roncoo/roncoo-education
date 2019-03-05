package com.roncoo.education.course.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.dao.CourseUserStudyLogDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseUserStudyLogMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyLog;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.util.tools.SqlUtil;

@Repository
public class CourseUserStudyLogDaoImpl implements CourseUserStudyLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CourseUserStudyLogMapper courseUserStudyLogMapper;

	@Override
	public int save(CourseUserStudyLog record) {
		record.setId(IdWorker.getId());
		return this.courseUserStudyLogMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.courseUserStudyLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseUserStudyLog record) {
		return this.courseUserStudyLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseUserStudyLog getById(Long id) {
		return this.courseUserStudyLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseUserStudyLog> listForPage(int pageCurrent, int pageSize, CourseUserStudyLogExample example) {
		int count = this.courseUserStudyLogMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseUserStudyLog>(count, totalPage, pageCurrent, pageSize, this.courseUserStudyLogMapper.selectByExample(example));
	}

	@Override
	public CourseUserStudyLog getByUserNoAndPeriodId(Long userNo, Long periodId) {
		CourseUserStudyLogExample example = new CourseUserStudyLogExample();
		example.createCriteria().andUserNoEqualTo(userNo).andPeriodIdEqualTo(periodId);
		List<CourseUserStudyLog> list = this.courseUserStudyLogMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	private <T> List<T> queryForObjectList(String sql, Class<T> clazz, Object... args) {
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(clazz));
	}

	@Override
	public Page<CourseUserStudyLog> courseList(int PageCurrent, int PageSize, String beginGmtCreate, String endGmtCreate) {
		StringBuilder sql = new StringBuilder();
		sql.append("select course_name as courseName, course_id as courseId, count(course_id) as chapterId from course_user_study_log ");
		if (!StringUtils.isEmpty(beginGmtCreate) && !StringUtils.isEmpty(endGmtCreate)) {
			sql.append("where gmt_create >= '").append(beginGmtCreate).append(" 00:00:00' and ").append("gmt_create <= '").append(endGmtCreate).append(" 23:59:59' ").append("group by course_id order by chapterId desc limit ?,?");
		} else if (!StringUtils.isEmpty(beginGmtCreate)) {
			sql.append("where gmt_create >= '").append(beginGmtCreate).append(" 00:00:00' ").append("group by course_id order by chapterId desc limit ?,?");
		} else if (!StringUtils.isEmpty(endGmtCreate)) {
			sql.append("where gmt_create <= '").append(endGmtCreate).append(" 23:59:59' ").append("group by course_id order by chapterId desc limit ?,?");
		} else {
			sql.append("group by course_id order by chapterId desc limit ?,?");
		}
		int totalCount = courseTotalCount(beginGmtCreate, endGmtCreate);
		int start = SqlUtil.countOffset(PageCurrent, PageSize);
		int pageSize = PageUtil.checkPageSize(PageSize);
		int pageCurrent = SqlUtil.checkPageCurrent(totalCount, pageSize, PageCurrent);
		int totalPage = SqlUtil.countTotalPage(totalCount, pageSize);
		List<CourseUserStudyLog> list = queryForObjectList(sql.toString(), CourseUserStudyLog.class, start, pageSize);
		Page<CourseUserStudyLog> page = new Page<>(totalCount, totalPage, pageCurrent, pageSize, list);
		return page;
	}

	private int courseTotalCount(String beginGmtCreate, String endGmtCreate) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(beginGmtCreate) && !StringUtils.isEmpty(endGmtCreate)) {
			sql.append("select count(distinct course_id) as count from course_user_study_log ").append("where gmt_create >= '").append(beginGmtCreate).append(" 00:00:00' and ").append("gmt_create <= '").append(endGmtCreate).append(" 23:59:59'");
		} else if (!StringUtils.isEmpty(beginGmtCreate)) {
			sql.append("select count(distinct course_id) as count from course_user_study_log ").append("where gmt_create >= '").append(beginGmtCreate).append(" 00:00:00'");
		} else if (!StringUtils.isEmpty(endGmtCreate)) {
			sql.append("select count(distinct course_id) as count from course_user_study_log ").append("where gmt_create <= '").append(endGmtCreate).append(" 23:59:59'");
		} else {
			sql.append("select count(distinct course_id) as count from course_user_study_log ");
		}
		map = jdbcTemplate.queryForMap(sql.toString());
		Integer count = 0;
		if (!StringUtils.isEmpty(map.get("count"))) {
			count = Integer.valueOf(String.valueOf(map.get("count")));
		}
		return count;
	}

	@Override
	public List<CourseUserStudyLog> countCourseIdByGmtCreate(String beginGmtCreate, String endGmtCreate) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(course_id) as courseId, course_name as courseName from course_user_study_log ");
		if (!StringUtils.isEmpty(beginGmtCreate) && !StringUtils.isEmpty(endGmtCreate)) {
			sql.append("where gmt_create >= '").append(beginGmtCreate).append(" 00:00:00' and ").append("gmt_create <= '").append(endGmtCreate).append(" 23:59:59' ").append(" group by course_id order by courseId desc limit 0,5");
		} else if (!StringUtils.isEmpty(beginGmtCreate)) {
			sql.append("where gmt_create >= '").append(beginGmtCreate).append(" 00:00:00'").append(" group by course_id order by courseId desc limit 0,5");
		} else if (!StringUtils.isEmpty(endGmtCreate)) {
			sql.append("where gmt_create <= '").append(endGmtCreate).append(" 23:59:59'").append(" group by course_id order by courseId desc limit 0,5");
		} else {
			sql.append(" group by course_id order by courseId desc limit 0,5");
		}
		return queryForObjectList(sql.toString(), CourseUserStudyLog.class);
	}

	@Override
	public Page<CourseUserStudyLog> periodList(Long courseId, int PageCurrent, int PageSize, String beginGmtCreate, String endGmtCreate) {
		StringBuilder sql = new StringBuilder();
		sql.append("select period_name as periodName, period_id as periodId, count(period_id) as chapterId from course_user_study_log where ");
		if (!StringUtils.isEmpty(beginGmtCreate)) {
			sql.append(" gmt_create >= '").append(beginGmtCreate).append(" 00:00:00' and ");
		}
		if (!StringUtils.isEmpty(endGmtCreate)) {
			sql.append(" gmt_create <= '").append(endGmtCreate).append(" 23:59:59' and ");
		}
		sql.append("course_id = ? group by period_id order by chapterId desc limit ?,?");
		int totalCount = periodTotalCount(courseId, beginGmtCreate, endGmtCreate);
		int start = SqlUtil.countOffset(PageCurrent, PageSize);
		int pageSize = PageUtil.checkPageSize(PageSize);
		int pageCurrent = SqlUtil.checkPageCurrent(totalCount, pageSize, PageCurrent);
		int totalPage = SqlUtil.countTotalPage(totalCount, pageSize);
		List<CourseUserStudyLog> list = queryForObjectList(sql.toString(), CourseUserStudyLog.class, courseId, start, pageSize);
		Page<CourseUserStudyLog> page = new Page<>(totalCount, totalPage, pageCurrent, pageSize, list);
		return page;
	}

	private int periodTotalCount(Long courseId, String beginGmtCreate, String endGmtCreate) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(distinct period_id) as count from course_user_study_log where ");
		if (!StringUtils.isEmpty(beginGmtCreate)) {
			sql.append("gmt_create >= '").append(beginGmtCreate).append(" 00:00:00' and ");
		}
		if (!StringUtils.isEmpty(endGmtCreate)) {
			sql.append("gmt_create <= '").append(endGmtCreate).append(" 23:59:59' and ");
		}
		sql.append("course_id = ?");
		Integer count = 0;
		Map<String, Object> map = jdbcTemplate.queryForMap(sql.toString(), courseId);
		if (!StringUtils.isEmpty(map.get("count"))) {
			count = Integer.valueOf(String.valueOf(map.get("count")));
		}
		return count;
	}

	@Override
	public List<CourseUserStudyLog> countPeriodNoByCourseIdAndGmtCreate(Long courseId, String beginGmtCreate, String endGmtCreate) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(period_id) as periodId, period_name as periodName from course_user_study_log where ");
		if (!StringUtils.isEmpty(beginGmtCreate)) {
			sql.append("gmt_create >= '").append(beginGmtCreate).append(" 00:00:00' and ");
		}
		if (!StringUtils.isEmpty(endGmtCreate)) {
			sql.append("gmt_create <= '").append(endGmtCreate).append(" 23:59:59' and ");
		}
		sql.append("course_id = ? group by period_id order by periodId desc limit 0,5");
		return queryForObjectList(sql.toString(), CourseUserStudyLog.class, courseId);
	}
}