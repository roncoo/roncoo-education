package com.roncoo.education.course.service.biz.web;

import java.io.File;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.common.bean.qo.CourseVideoQO;
import com.roncoo.education.course.common.bean.vo.CourseVideoVO;
import com.roncoo.education.course.service.dao.CourseVideoDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseVideoExample;
import com.roncoo.education.system.common.bean.vo.WebsiteVO;
import com.roncoo.education.system.feign.web.IBossWebsite;
import com.roncoo.education.util.aliyun.AliyunOasUtil;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.PolyvStatusEnum;
import com.roncoo.education.util.enums.VideoStatusEnum;
import com.roncoo.education.util.polyv.PolyvUtil;
import com.roncoo.education.util.polyv.UploadFile;
import com.roncoo.education.util.polyv.UploadFileResult;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Component
public class BossCourseVideoBiz {

	@Autowired
	private CourseVideoDao dao;

	@Autowired
	private IBossWebsite bossWebsite;

	public Page<CourseVideoVO> listForPage(CourseVideoQO qo) {
		CourseVideoExample example = new CourseVideoExample();
		example.setOrderByClause(" id desc ");
		Page<CourseVideo> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, CourseVideoVO.class);
	}

	public int save(CourseVideoQO qo) {
		CourseVideo record = BeanUtil.copyProperties(qo, CourseVideo.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public CourseVideoVO getById(Long id) {
		CourseVideo record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseVideoVO.class);
	}

	public int updateById(CourseVideoQO qo) {
		CourseVideo record = BeanUtil.copyProperties(qo, CourseVideo.class);
		return dao.updateById(record);
	}

	/**
	 * 定时任务-视频处理
	 * 
	 * @param targetFile
	 * @author wuyun
	 */
	public void handleScheduledTasks(File targetFile) {
		Long videoNo = Long.valueOf(StrUtil.getPrefix(targetFile.getName()));
		List<CourseVideo> list = dao.listByVideoNo(videoNo);

		// 1、异步上传到保利威视
		UploadFile uploadFile = new UploadFile();
		CourseVideo courseVideo = list.get(0);
		uploadFile.setTitle(courseVideo.getVideoName());
		uploadFile.setDesc(courseVideo.getVideoName());
		uploadFile.setTag(courseVideo.getVideoName());
		uploadFile.setCataid(1L);

		WebsiteVO websiteVO = bossWebsite.getWebsite();
		if (PolyvStatusEnum.ENABLE.getCode().equals(websiteVO.getPolyvStatus())) {
			uploadFile.setWatermark(websiteVO.getPolyvLogo());
		}
		UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile);

		if (ObjectUtil.isNotNull(result)) {
			// 2、异步上传到阿里云
			String videoOasId = AliyunOasUtil.upload(targetFile);
			if (CollectionUtils.isNotEmpty(list)) {
				for (CourseVideo info : list) {
					// 上传
					info.setVideoLength(result.getDuration());
					info.setVideoVid(result.getVid());
					info.setVideoOasId(videoOasId);
					info.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
					dao.updateById(info);
				}
			}
		}
		// 成功删除本地文件
		targetFile.delete();
	}

}
