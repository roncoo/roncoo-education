package com.roncoo.education.course.service.biz.gateway.auth;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseVideoDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseVideoForUpdateBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseVideoSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseVideoUpdateBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseVideoForListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseVideoListDTO;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.CourseVideoDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.IsVideoEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.polyv.PolyvUtil;
import com.roncoo.education.util.tools.ArrayListUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Component
public class AuthApiCourseVideoBiz extends BaseBiz {

	@Autowired
	private CourseVideoDao dao;
	@Autowired
	private CourseChapterPeriodAuditDao periodAuditDao;
	@Autowired
	private CourseChapterAuditDao chapterAuditDao;
	@Autowired
	private CourseAuditDao courseAuditDao;

	/**
	 * 章节视频库，课时视频库添加接口
	 * 
	 * @param authCourseVideoSaveBo
	 * @author wuyun
	 */
	public Result<Integer> save(AuthCourseVideoSaveBO bo) {
		if (bo.getChapterId().equals(Long.valueOf(0))) {
			return Result.error("章节ID不能为空");
		}
		if (bo.getUserNo() == null) {
			return Result.error("useNo不能为空");
		}
		// 根据章节编号查找章节信息
		CourseChapterAudit chapterInfoAudit = chapterAuditDao.getById(bo.getChapterId());
		if (ObjectUtil.isNull(chapterInfoAudit)) {
			return Result.error("找不到章节信息");
		}
		CourseAudit courseAudit = courseAuditDao.getById(chapterInfoAudit.getCourseId());
		if (ObjectUtil.isNull(courseAudit)) {
			return Result.error("找不到课程信息");
		}
		if (!bo.getUserNo().equals(courseAudit.getLecturerUserNo())) {
			return Result.error("传入的useNo与该课程的讲师useNo不一致");
		}
		// 查找视频信息
		CourseVideo courseVideo = dao.getByVideoNo(bo.getVideoNo());
		if (ObjectUtil.isNull(courseVideo)) {
			return Result.error("找不到课时视频信息");
		}
		// 更改视频信息信息
		if (bo.getPeriodId() != null) {
			// 如果传进来的课时编号不为空，则为课时视频库
			courseVideo.setPeriodId(bo.getPeriodId());
		}
		courseVideo.setCourseId(chapterInfoAudit.getCourseId());
		courseVideo.setChapterId(chapterInfoAudit.getId());
		courseVideo.setStatusId(StatusIdEnum.YES.getCode());
		int result = dao.updateById(courseVideo);
		if (result > 0) {
			return Result.success(result);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 章节视频库列出
	 * 
	 * @param chapterId
	 * @author wuyun
	 */
	public Result<AuthCourseVideoListDTO> listByChapterId(Long chapterId) {
		// 查找该章节下可用的视频信息集合
		List<CourseVideo> courseVideoList = dao.listByChapterIdAndPeriodIdAndStatusId(chapterId, Long.valueOf(0), StatusIdEnum.YES.getCode());
		AuthCourseVideoListDTO dto = new AuthCourseVideoListDTO();
		if (CollectionUtil.isNotEmpty(courseVideoList)) {
			List<AuthCourseVideoForListDTO> dtoList = ArrayListUtil.copy(courseVideoList, AuthCourseVideoForListDTO.class);
			dto.setList(dtoList);
		}
		return Result.success(dto);
	}

	/**
	 * 课时视频列出
	 * 
	 * @param periodId
	 * @author wuyun
	 */
	public Result<AuthCourseVideoListDTO> listByPeriodId(Long periodId) {
		List<CourseVideo> courseVideoList = dao.listByPeriodIdAndStatusId(periodId, StatusIdEnum.YES.getCode());
		AuthCourseVideoListDTO dto = new AuthCourseVideoListDTO();
		if (CollectionUtil.isNotEmpty(courseVideoList)) {
			List<AuthCourseVideoForListDTO> dtoList = ArrayListUtil.copy(courseVideoList, AuthCourseVideoForListDTO.class);
			dto.setList(dtoList);
		}
		return Result.success(dto);
	}

	/**
	 * 课时视频更新接口
	 * 
	 * @param authCourseVideoUpdateBo
	 * @author wuyun
	 */
	@Transactional
	public Result<Integer> update(AuthCourseVideoUpdateBO bo) {
		if (bo.getPeriodId() == null) {
			return Result.error("periodId不能为空");
		}
		if (bo.getUserNo() == null) {
			return Result.error("useNo不能为空");
		}
		CourseChapterPeriodAudit courseChapterPeriodAudit = periodAuditDao.getById(bo.getPeriodId());
		if (ObjectUtil.isNull(courseChapterPeriodAudit)) {
			return Result.error("找不到课时信息");
		}
		CourseAudit course = courseAuditDao.getById(courseChapterPeriodAudit.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!bo.getUserNo().equals(course.getLecturerUserNo())) {
			return Result.error("传入的useNo与该课程的讲师useNo不一致");
		}

		Long videoNo = null;
		String videoName = null;
		String videoLength = null;
		String videovid = null;
		List<CourseVideo> courseVideoList = dao.listByPeriodIdAndStatusId(bo.getPeriodId(), StatusIdEnum.YES.getCode());
		if (CollectionUtil.isEmpty(courseVideoList)) {
			return Result.error("找不到该课时的视频信息");
		}
		for (CourseVideo courseVideo : courseVideoList) {
			videoNo = courseVideo.getVideoNo();
			videoName = courseVideo.getVideoName();
			videoLength = courseVideo.getVideoLength();
			videovid = courseVideo.getVideoVid();
		}

		// 根据课时ID查询课程视频信息集合
		List<CourseVideo> CourseVideoList = dao.listByPeriodId(bo.getPeriodId());
		for (CourseVideo courseVideo : CourseVideoList) {
			CourseVideo video = new CourseVideo();
			video.setId(courseVideo.getId());
			video.setStatusId(FREEZE);
			dao.updateById(video);
		}

		// 更新课时审核信息为待审核状态
		courseChapterPeriodAudit.setIsVideo(IsVideoEnum.YES.getCode());
		courseChapterPeriodAudit.setVideoNo(videoNo);
		courseChapterPeriodAudit.setVideoName(videoName);
		courseChapterPeriodAudit.setVideoLength(videoLength);
		courseChapterPeriodAudit.setVideoVid(videovid);
		courseChapterPeriodAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		periodAuditDao.updateById(courseChapterPeriodAudit);
		// 更新章节审核信息为待审核状态
		CourseChapterAudit chapterAudit = new CourseChapterAudit();
		chapterAudit.setId(courseChapterPeriodAudit.getChapterId());
		chapterAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		chapterAuditDao.updateById(chapterAudit);
		// 更新课程审核信息为待审核状态
		CourseAudit courseAudit = new CourseAudit();
		courseAudit.setId(courseChapterPeriodAudit.getCourseId());
		courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		courseAuditDao.updateById(courseAudit);

		// 如果视频集合不为空
		if (CollectionUtil.isNotEmpty(bo.getList())) {
			for (AuthCourseVideoForUpdateBO updateBo : bo.getList()) {
				// 根据视频编号课时编号查找课时视频信息
				CourseVideo courseVideo = dao.getByVideoNoAndPeriodId(updateBo.getVideoNo(), bo.getPeriodId());
				if (ObjectUtil.isNotNull(courseVideo)) {
					// 如果存在且为禁用状态则更新为可用状态
					courseVideo.setStatusId(StatusIdEnum.YES.getCode());
					dao.updateById(courseVideo);
				} else {
					// 根据视频编号查找可用的课时视频信息
					CourseVideo infoAudit = dao.getByVideoNoAndStatusId(updateBo.getVideoNo(), StatusIdEnum.YES.getCode());
					CourseVideo audit = BeanUtil.copyProperties(infoAudit, CourseVideo.class);
					audit.setPeriodId(bo.getPeriodId());
					dao.save(audit);
				}
			}
			return Result.success(bo.getList().size());
		} else {
			// 如果为空则直接返回成功
			return Result.success(0);
		}
	}

	/**
	 * 视频删除
	 * 
	 * @param bo
	 * @author wuyun
	 */
	@Transactional
	public Result<Integer> udpateById(AuthCourseVideoDeleteBO bo) {
		if (bo.getId() == null) {
			return Result.error("id不能为空");
		}
		if (bo.getUserNo() == null) {
			return Result.error("lecturerUserNo不能为空");
		}
		CourseVideo courseVideo = dao.getById(bo.getId());
		if (ObjectUtil.isNull(courseVideo)) {
			return Result.error("找不到该视频");
		}
		CourseAudit course = courseAuditDao.getById(courseVideo.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!bo.getUserNo().equals(course.getLecturerUserNo())) {
			return Result.error("传入的useNo与该课程的讲师useNo不一致");
		}
		// 查找该视频是否被使用
		List<CourseVideo> courseVideoList = dao.listByVideoNoAndNotEqualPeriodIdAndStatusId(courseVideo.getVideoNo(), Long.valueOf(0), StatusIdEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(courseVideoList) && courseVideoList.size() >= 2) {
			return Result.error("该视频已被使用，请先删除");
		}
		// 更改为冻结状态
		courseVideo.setStatusId(FREEZE);
		int result = dao.updateById(courseVideo);
		if (result > 0) {
			// 删除保利威视的视频
			PolyvUtil.deleteFile(courseVideo.getVideoVid(), bo.getUseid(), bo.getUecretkey());
			if (!courseVideo.getPeriodId().equals(Long.valueOf(0))) {
				CourseChapterPeriodAudit periodInfoAudit = periodAuditDao.getById(courseVideo.getPeriodId());
				periodInfoAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
				periodAuditDao.updateById(periodInfoAudit);
			}
			CourseChapterAudit chapterInfo = chapterAuditDao.getById(courseVideo.getChapterId());
			chapterInfo.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			chapterAuditDao.updateById(chapterInfo);

			CourseAudit courseAudit = new CourseAudit();
			courseAudit.setId(chapterInfo.getCourseId());
			courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseAuditDao.updateById(courseAudit);
			return Result.success(result);
		}

		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

}
