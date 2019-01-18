package com.roncoo.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.common.bean.qo.CourseQO;
import com.roncoo.education.course.common.bean.vo.CourseVO;
import com.roncoo.education.course.feign.IBossCourse;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.web.boss.common.BizBoss;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class CourseBiz extends BizBoss {

	@Autowired
	private IBossSys bossSys;
	@Autowired
	private IBossCourse bossCourse;

	public Page<CourseVO> listForPage(CourseQO qo) {
		return bossCourse.listForPage(qo);
	}

	public int save(CourseQO qo) {
		return bossCourse.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourse.deleteById(id);
	}

	public CourseVO getById(Long id) {
		return bossCourse.getById(id);
	}

	public int updateById(CourseQO qo, MultipartFile advFile) {
		if (null != advFile && !advFile.isEmpty()) {
			qo.setCourseLogo(AliyunUtil.uploadPic(PlatformEnum.COURSE, advFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return bossCourse.updateById(qo);
	}

	public CourseVO getByCourseId(Long id) {
		return bossCourse.getByCourseId(id);
	}

	public int updateByStatusId(CourseQO qo) {
		return bossCourse.updateById(qo);
	}

}
