package com.roncoo.education.course.service.api.pc.biz;

import com.roncoo.education.course.common.req.*;
import com.roncoo.education.course.common.resq.*;
import com.roncoo.education.course.service.dao.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAuditExample.Criteria;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.user.feign.vo.LecturerVO;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 课程信息-审核
 */
@Component
public class PcApiCourseAuditBiz {

	@Autowired
	private IFeignLecturer bossLecturer;
	@Autowired
	private IFeignSys bossSys;

	@Autowired
	private CourseAuditDao dao;
	@Autowired
	private CourseCategoryDao courseCategoryDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseChapterAuditDao courseChapterAuditDao;
	@Autowired
	private CourseChapterDao courseChapterDao;
	@Autowired
	private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
	@Autowired
	private CourseChapterPeriodDao courseChapterPeriodDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private CourseIntroduceAuditDao courseIntroduceAuditDao;

	public Result<Page<CourseAuditPageRESQ>> list(CourseAuditPageREQ req) {
		CourseAuditExample example = new CourseAuditExample();
		Criteria c = example.createCriteria();

		if (!StringUtils.isEmpty(req.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(req.getCourseName()));
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		if (req.getAuditStatus() == null) {
			c.andAuditStatusNotEqualTo(AuditStatusEnum.SUCCESS.getCode());
		} else {
			c.andAuditStatusEqualTo(req.getAuditStatus());
		}
		if (req.getIsFree() != null) {
			c.andIsFreeEqualTo(req.getIsFree());
		}
		if (req.getIsPutaway() != null) {
			c.andIsPutawayEqualTo(req.getIsPutaway());
		}

		example.setOrderByClause(" audit_status asc, status_id desc, is_putaway desc, sort desc, id desc ");
		Page<CourseAudit> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CourseAuditPageRESQ> listForPage = PageUtil.transform(page, CourseAuditPageRESQ.class);
		// 获取分类名称
		for (CourseAuditPageRESQ resq : listForPage.getList()) {
			if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName1(courseCategory.getCategoryName());
				}
			}
			if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName2(courseCategory.getCategoryName());
				}
			}
			if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName3(courseCategory.getCategoryName());
				}
			}
		}
		return Result.success(listForPage);
	}

	/**
	 * 更新
	 *
	 * @param req
	 * @return
	 */
	@Transactional
	public Result<Integer> update(CourseAuditUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		CourseAudit courseAudit = dao.getById(req.getId());
		if (ObjectUtil.isNull(courseAudit)) {
			return Result.error("找不到课程信息");
		}
		if (IsFreeEnum.FREE.getCode().equals(req.getIsFree())) {
			req.setCourseOriginal(BigDecimal.ZERO);
			req.setCourseDiscount(BigDecimal.ZERO);
		}
		CourseAudit record = BeanUtil.copyProperties(req, CourseAudit.class);
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		int result = dao.updateById(record);
		if (result < 0) {
			return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
		}
		if (StringUtils.hasText(req.getIntroduce())) {
			// 查询课程简介
			CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(courseAudit.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduceAudit)) {
				return Result.error("找不到课程简介信息");
			}
			// 更新课程简介
			courseIntroduceAudit.setIntroduce(req.getIntroduce());
			int courseIntroduceResult = courseIntroduceAuditDao.updateById(courseIntroduceAudit);
			if (courseIntroduceResult < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
		}
		return Result.success(result);
	}

	/**
	 * 查看(课程修改使用)
	 *
	 * @param req
	 * @return
	 */
	public Result<CourseAuditGetRESQ> get(CourseAuditGetREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		CourseAudit record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到课程信息");
		}
		CourseAuditGetRESQ resq = BeanUtil.copyProperties(record, CourseAuditGetRESQ.class);
		// 获取分类名称
		if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
			resq.setCategoryName1(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
			resq.setCategoryName2(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
			resq.setCategoryName3(courseCategory.getCategoryName());
		}
		// 课程简介
		CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(resq.getIntroduceId());
		resq.setIntroduceId(courseIntroduceAudit.getId());
		resq.setIntroduce(courseIntroduceAudit.getIntroduce());

		// 查询讲师信息
		LecturerVO lecturerresq = bossLecturer.getByLecturerUserNo(resq.getLecturerUserNo());
		if (ObjectUtil.isNull(lecturerresq)) {
			throw new BaseException("找不到讲师信息");
		}
		resq.setLecturerName(lecturerresq.getLecturerName());
		return Result.success(resq);
	}

	/**
	 * 查看详情
	 *
	 * @param req
	 * @return
	 */
	public Result<CourseAuditViewRESQ> view(CourseAuditViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		CourseAudit record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到课程信息");
		}
		CourseAuditViewRESQ resq = BeanUtil.copyProperties(record, CourseAuditViewRESQ.class);
		// 获取分类名称
		if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
			resq.setCategoryName1(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
			resq.setCategoryName2(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
			resq.setCategoryName3(courseCategory.getCategoryName());
		}
		// 课程简介
		CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(resq.getIntroduceId());
		resq.setIntroduceId(courseIntroduceAudit.getId());
		resq.setIntroduce(courseIntroduceAudit.getIntroduce());

		// 查询讲师信息
		LecturerVO lecturerresq = bossLecturer.getByLecturerUserNo(resq.getLecturerUserNo());
		if (ObjectUtil.isNull(lecturerresq)) {
			throw new BaseException("找不到讲师信息");
		}
		resq.setLecturerName(lecturerresq.getLecturerName());
		// 章节
		List<CourseChapterAudit> ChapterList = courseChapterAuditDao.listByCourseIdAndStatusId(req.getId(), StatusIdEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(ChapterList)) {
			List<CourseChapterAuditViewRESQ> courseChapterList = PageUtil.copyList(ChapterList, CourseChapterAuditViewRESQ.class);
			for (CourseChapterAuditViewRESQ courseChapter : courseChapterList) {
				// 课时
				List<CourseChapterPeriodAudit> periodList = courseChapterPeriodAuditDao.listByChapterIdAndStatusId(courseChapter.getId(), StatusIdEnum.YES.getCode());
				courseChapter.setCourseChapterPeriodAuditList(PageUtil.copyList(periodList, CourseChapterPeriodAuditViewRESQ.class));
			}
			resq.setCourseChapterAuditList(courseChapterList);
		}
		return Result.success(resq);
	}

	public Result<Integer> audit(CourseAuditAuditStatusREQ req) {
		if (req.getId() == null) {
			return Result.error("课程ID");
		}
		if (req.getAuditStatus() == null) {
			return Result.error("审核状态不能为空");
		}
		// 不成功
		if (!AuditStatusEnum.SUCCESS.getCode().equals(req.getAuditStatus())) {
			CourseAudit audit = BeanUtil.copyProperties(req, CourseAudit.class);
			return Result.success(dao.updateById(audit));
		}
		// 审核 课程-章节-课时
		CourseAudit courseAudit = dao.getById(req.getId());
		if (ObjectUtil.isNull(courseAudit)) {
			return Result.error("课程不存在");
		}

		// 根据课程ID查询课时信息集合
		List<CourseChapterPeriodAudit> periodAuditList = courseChapterPeriodAuditDao.listByCourseId(courseAudit.getId());

		Course course = courseDao.getById(courseAudit.getId());
		// 1、对课程操作
		// 如果课程信息表里面有数据就进行更新
		if (ObjectUtil.isNotNull(course)) {
			course = BeanUtil.copyProperties(courseAudit, Course.class);
			course.setGmtCreate(null);
			course.setGmtModified(null);
			// 设置总课时数
			if (CollectionUtils.isEmpty(periodAuditList)) {
				course.setPeriodTotal(0);
			} else {
				course.setPeriodTotal(periodAuditList.size());
			}
			// 更新课程信息表
			courseDao.updateById(course);
		} else {
			// 如果课程信息表里面没数据就进行插入
			Course info = BeanUtil.copyProperties(courseAudit, Course.class);
			info.setGmtCreate(null);
			info.setGmtModified(null);
			// 设置总课时数
			if (CollectionUtils.isEmpty(periodAuditList)) {
				info.setPeriodTotal(0);
			} else {
				info.setPeriodTotal(periodAuditList.size());
			}
			courseDao.save(info);
		}

		// 2、对课程简介操作
		CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(courseAudit.getIntroduceId());
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(courseAudit.getIntroduceId());
		if (ObjectUtil.isNull(courseIntroduceAudit)) {
			return Result.error("课程简介信息表不存在");
		}
		if (ObjectUtil.isNull(courseIntroduce)) {
			CourseIntroduce introduce = BeanUtil.copyProperties(courseIntroduceAudit, CourseIntroduce.class);
			courseIntroduceDao.save(introduce);
		} else {
			courseIntroduce = BeanUtil.copyProperties(courseIntroduceAudit, CourseIntroduce.class);
			courseIntroduceAuditDao.updateById(courseIntroduceAudit);
		}
		// 3、对章节操作
		chapter(courseAudit);

		// 4、对课时操作
		period(courseAudit);

		// 更改课程审核状态
		CourseAudit audit = BeanUtil.copyProperties(req, CourseAudit.class);
		int resultNum = dao.updateById(audit);
		if (resultNum < 0) {
			return Result.error(ResultEnum.COURSE_AUDIT_FAIL);
		}
		return Result.success(resultNum);
	}

	// 审核章节
	private void chapter(CourseAudit courseAudit) {
		// 根据课程编号查找章节审核信息集合
		List<CourseChapterAudit> courseChapterAuditList = courseChapterAuditDao.listByCourseId(courseAudit.getId());
		if (CollectionUtils.isEmpty(courseChapterAuditList)) {
			return;
		}
		for (CourseChapterAudit courseChapterAudit : courseChapterAuditList) {
			// 根据章节编号查找章节审核信息
			CourseChapterAudit infoAudit = courseChapterAuditDao.getById(courseChapterAudit.getId());
			if (ObjectUtil.isNull(infoAudit)) {
				throw new BaseException("找不到章节审核信息");
			}
			// 查找章节信息表是否存在该课时信息
			CourseChapter chapter = courseChapterDao.getById(courseChapterAudit.getId());
			// 存在就更新章节信息表数据
			if (ObjectUtil.isNotNull(chapter)) {
				chapter = BeanUtil.copyProperties(infoAudit, CourseChapter.class);
				chapter.setGmtCreate(null);
				chapter.setGmtModified(null);
				courseChapterDao.updateById(chapter);
			} else {
				// 如果章节不存在则插入章节信息
				chapter = BeanUtil.copyProperties(infoAudit, CourseChapter.class);
				chapter.setGmtCreate(null);
				chapter.setGmtModified(null);
				courseChapterDao.save(chapter);
			}
			// 更新审核状态
			infoAudit.setAuditStatus(AuditStatusEnum.SUCCESS.getCode());
			courseChapterAuditDao.updateById(infoAudit);
		}
	}

	// 课时审核
	private void period(CourseAudit courseAudit) {
		// 根据课程编号查找课时审核信息集合
		List<CourseChapterPeriodAudit> courseChapterPeriodAuditList = courseChapterPeriodAuditDao.listByCourseId(courseAudit.getId());
		if (CollectionUtils.isEmpty(courseChapterPeriodAuditList)) {
			return;
		}
		for (CourseChapterPeriodAudit courseChapterPeriodAudit : courseChapterPeriodAuditList) {
			// 根据课时编号查找课时审核信息
			CourseChapterPeriodAudit chapterperiodAudit = courseChapterPeriodAuditDao.getById(courseChapterPeriodAudit.getId());
			if (ObjectUtil.isNull(chapterperiodAudit)) {
				throw new BaseException("找不到课时审核信息");
			}
			// 根据课时编号查找课时信息
			CourseChapterPeriod chapterPeriod = courseChapterPeriodDao.getById(courseChapterPeriodAudit.getId());
			// 如果信息表存在就更新信息表信息
			if (ObjectUtil.isNotNull(chapterPeriod)) {
				if (IsDocEnum.NO.getCode().equals(chapterPeriod.getIsDoc())) {
					AliyunUtil.delete(chapterPeriod.getDocUrl(), BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class));
				}
				chapterPeriod = BeanUtil.copyProperties(chapterperiodAudit, CourseChapterPeriod.class);
				chapterPeriod.setGmtCreate(null);
				chapterPeriod.setGmtModified(null);
				courseChapterPeriodDao.updateById(chapterPeriod);
			} else {
				// 如果课时信息表不存在就插入信息
				chapterPeriod = BeanUtil.copyProperties(chapterperiodAudit, CourseChapterPeriod.class);
				chapterPeriod.setGmtCreate(null);
				chapterPeriod.setGmtModified(null);
				courseChapterPeriodDao.save(chapterPeriod);
			}
			// 更新审核状态
			chapterperiodAudit.setAuditStatus(AuditStatusEnum.SUCCESS.getCode());
			courseChapterPeriodAuditDao.updateById(chapterperiodAudit);
		}
	}

}
