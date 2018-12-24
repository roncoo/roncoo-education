/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.biz.gateway;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.CourseVideoDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.web.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.ConfigUtil;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.IsDocEnum;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.enums.VideoStatusEnum;
import com.roncoo.education.util.polyv.PolyvUtil;
import com.roncoo.education.util.polyv.UploadFile;
import com.roncoo.education.util.polyv.UploadFileResult;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 上传接口
 * 
 * @author wuyun
 */
@Component
public class ApiUploadBiz extends BaseBiz {

	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CourseChapterAuditDao courseChapterAuditDao;
	@Autowired
	private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
	@Autowired
	private CourseVideoDao courseVideoDao;

	@Autowired
	private IBossSys bossSys;

	/**
	 * 上传视频接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadVideo(MultipartFile videoFile) {
		// 视频上传
		if (videoFile == null || videoFile.isEmpty()) {
			return Result.error("请选择视频进行上传");
		}

		// 获取上传文件的原名
		String fileName = videoFile.getOriginalFilename();
		boolean fileStatus = true;
		List<String> fileTypes = Arrays.asList("avi", "mp4", "flv", "mpg", "mov", "asf", "3gp", "f4v", "wmv", "x-ms-wmv\n");
		for (String filetype : fileTypes) {
			// 上传文件的原名+小写+后缀
			if (fileName.toLowerCase().endsWith(filetype)) {
				fileStatus = false;
				break;
			}
		}
		if (fileStatus) {
			return Result.error("上传的视频类型不正确");
		}

		Long videoNo = IdWorker.getId(); // 当作存储到本地的文件名，方便定时任务的处理

		// 1、上传到本地
		File targetFile = new File(ConfigUtil.PERIOD_VIDEO_PATH + videoNo.toString() + "." + StrUtil.getSuffix(fileName));
		targetFile.setLastModified(System.currentTimeMillis());// 设置最后修改时间
		// 判断文件目录是否存在，不存在就创建文件目录
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		try {
			videoFile.transferTo(targetFile);
		} catch (Exception e) {
			logger.error("上传到本地失败", e);
			return Result.error("上传文件出错，请重新上传");
		}

		// 新增课程视频表信息
		CourseVideo courseVideo = new CourseVideo();
		courseVideo.setVideoNo(videoNo);
		courseVideo.setGmtCreate(null);
		courseVideo.setGmtModified(null);
		courseVideo.setVideoName(fileName);
		courseVideo.setVideoStatus(VideoStatusEnum.WAIT.getCode());
		int result = courseVideoDao.save(courseVideo);

		if (result > 0) {
			callbackExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 2、异步上传到保利威视
					UploadFile uploadFile = new UploadFile();
					uploadFile.setTitle(fileName);
					uploadFile.setDesc(fileName);
					uploadFile.setTag(videoFile.getOriginalFilename());
					uploadFile.setCataid(1L);

					// 获取系统配置信息
					SysVO sys = bossSys.getSys();

					UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile, sys.getPolyvWritetoken());
					if (result == null) {
						// 上传异常，不再进行处理，定时任务会继续进行处理
						return;
					}

					courseVideo.setVideoLength(result.getDuration());
					courseVideo.setVideoVid(result.getVid());
					courseVideo.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
					courseVideoDao.updateById(courseVideo);

					// 3、异步上传到阿里云
					String videoOasId = AliyunUtil.uploadDoc(PlatformEnum.COURSE, targetFile, BeanUtil.copyProperties(sys, Aliyun.class));
					courseVideo.setVideoOasId(videoOasId);
					courseVideoDao.updateById(courseVideo);

					// 根据视频编号、课时ID查询课程视频信息
					CourseVideo courseVideo = courseVideoDao.getByVideoNoAndPeriodId(videoNo, Long.valueOf(0));

					// 根据视频编号更新视频信息
					List<CourseVideo> list = courseVideoDao.listByVideoNo(videoNo);
					for (CourseVideo video : list) {
						video.setVideoLength(courseVideo.getVideoLength());
						video.setVideoVid(courseVideo.getVideoVid());
						video.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
						video.setVideoOasId(courseVideo.getVideoOasId());
						courseVideoDao.updateById(video);
					}

					// 4、成功删除本地文件
					if (targetFile.isFile() && targetFile.exists()) {
						targetFile.delete();
					}
				}
			});
		} else {
			return Result.error("系统异常，请重试");
		}
		return Result.success(String.valueOf(courseVideo.getVideoNo()));
	}

	/**
	 * 上传图片接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadPic(MultipartFile picFile) {
		if (ObjectUtil.isNotNull(picFile) && !picFile.isEmpty()) {
			// 没水印，单独上传
			return Result.success(AliyunUtil.uploadPic(PlatformEnum.COURSE, picFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return Result.error("请选择上传的图片");
	}

	/**
	 * 上传文档接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadDoc(MultipartFile docFile, Long periodId) {
		if (ObjectUtil.isNotNull(docFile) && !docFile.isEmpty()) { // 文档上传
			CourseChapterPeriodAudit courseChapterPeriodAudit = courseChapterPeriodAuditDao.getById(periodId);
			if (ObjectUtil.isNull(courseChapterPeriodAudit)) {
				return Result.error("找不到课时信息");
			}
			SysVO sys = bossSys.getSys();
			if (ObjectUtil.isNull(sys)) {
				return Result.error("找不到系统配置信息");
			}
			if (StringUtils.isEmpty(sys.getAliyunAccessKeyId()) || StringUtils.isEmpty(sys.getAliyunAccessKeySecret())) {
				return Result.error("accessKeyId或者accessKeySecret没配置");
			}
			if (StringUtils.isEmpty(sys.getAliyunOasVault()) || StringUtils.isEmpty(sys.getAliyunOssBucket()) || StringUtils.isEmpty(sys.getAliyunOssUrl())) {
				return Result.error("oasVault,ossBucket或ossUrl没配置");
			}
			String url = AliyunUtil.uploadDoc(PlatformEnum.COURSE, docFile, BeanUtil.copyProperties(sys, Aliyun.class));

			// 更新课程审核表为待审核状态
			CourseAudit courseAudit = new CourseAudit();
			courseAudit.setId(courseChapterPeriodAudit.getCourseId());
			courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseAuditDao.updateById(courseAudit);
			// 更新章节审核表为待审核状态
			CourseChapterAudit courseChapterAudit = new CourseChapterAudit();
			courseChapterAudit.setId(courseChapterPeriodAudit.getChapterId());
			courseChapterAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseChapterAuditDao.updateById(courseChapterAudit);
			// 更新课时审核信息
			CourseChapterPeriodAudit periodAudit = new CourseChapterPeriodAudit();
			periodAudit.setId(periodId);
			periodAudit.setIsDoc(Integer.valueOf(IsDocEnum.YES.getCode()));
			periodAudit.setDocName(docFile.getOriginalFilename());
			periodAudit.setDocUrl(url);
			periodAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseChapterPeriodAuditDao.updateById(periodAudit);

			return Result.success(url);
		}
		return Result.error("请选择上传的文件");

	}

}
