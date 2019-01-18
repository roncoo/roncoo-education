package com.roncoo.education.web.boss.biz.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.common.bean.qo.CourseQO;
import com.roncoo.education.course.common.bean.qo.ZoneCourseQO;
import com.roncoo.education.course.common.bean.vo.CourseVO;
import com.roncoo.education.course.common.bean.vo.ZoneCourseVO;
import com.roncoo.education.course.common.bean.vo.ZoneVO;
import com.roncoo.education.course.feign.IBossCourse;
import com.roncoo.education.course.feign.IBossZone;
import com.roncoo.education.course.feign.IBossZoneCourse;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.common.interfaces.BossLecturer;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@Component
public class ZoneCourseBiz extends BaseBiz {

	@Autowired
	private IBossZoneCourse bossZoneCourse;
	@Autowired
	private IBossCourse bossCourse;
	@Autowired
	private BossLecturer bossLecturer;
	@Autowired
	private IBossZone bossZone;

	public Page<ZoneCourseVO> listForPage(ZoneCourseQO qo) {
		return bossZoneCourse.listForPage(qo);
	}

	public int save(ZoneCourseQO qo) {
		return bossZoneCourse.save(qo);
	}

	public int deleteById(Long id) {
		return bossZoneCourse.deleteById(id);
	}

	public ZoneCourseVO getById(Long id) {
		return bossZoneCourse.getById(id);
	}

	public int updateById(ZoneCourseQO qo) {
		return bossZoneCourse.updateById(qo);
	}

	public Page<CourseVO> list(CourseQO qo) {
		return bossCourse.listForPage(qo);
	}

	public CourseVO getBycourseId(Long courseId) {
		return bossCourse.getByCourseId(courseId);
	}

	public List<LecturerVO> listAllForLecturer() {
		return bossLecturer.listAllForLecturer();
	}

	public List<ZoneVO> listAllZone() {
		return bossZone.listAllZone();
	}

	public void check(CourseQO qo, Long zoneId) {
		ZoneCourseQO zoneCourseQO = new ZoneCourseQO();
		zoneCourseQO.setZoneId(zoneId);
		zoneCourseQO.setPageSize(100);
		Page<ZoneCourseVO> page = bossZoneCourse.listForPage(zoneCourseQO);
		if (page.getList().size() > 0) {
			List<Long> notInCourseNoList = new ArrayList<>();
			for (ZoneCourseVO zoneCourse : page.getList()) {
				notInCourseNoList.add(zoneCourse.getCourseId());
			}
			qo.setNotInCourseNoList(notInCourseNoList);
			qo.setZoneId(zoneId);
		}
	}
}
