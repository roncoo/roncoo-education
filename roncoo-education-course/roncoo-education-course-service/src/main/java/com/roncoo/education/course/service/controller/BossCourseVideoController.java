package com.roncoo.education.course.service.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.qo.CourseVideoQO;
import com.roncoo.education.course.common.bean.vo.CourseVideoVO;
import com.roncoo.education.course.common.interfaces.BossCourseVideo;
import com.roncoo.education.course.service.controller.biz.BossCourseVideoBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@RestController
public class BossCourseVideoController extends BaseController implements BossCourseVideo {

	@Autowired
	private BossCourseVideoBiz biz;

	@Override
	public Page<CourseVideoVO> listForPage(@RequestBody CourseVideoQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody CourseVideoQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody CourseVideoQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public CourseVideoVO getById(@RequestBody Long id) {
		return biz.getById(id);
	}

	@Override
	public void handleScheduledTasks(@RequestBody File targetFile) {
		biz.handleScheduledTasks(targetFile);
	}

}
