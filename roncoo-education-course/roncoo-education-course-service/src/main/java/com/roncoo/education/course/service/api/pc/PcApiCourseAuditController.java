package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.api.pc.biz.PcApiCourseAuditBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.req.CourseAuditAuditStatusREQ;
import com.roncoo.education.course.common.req.CourseAuditGetREQ;
import com.roncoo.education.course.common.req.CourseAuditPageREQ;
import com.roncoo.education.course.common.req.CourseAuditUpdateREQ;
import com.roncoo.education.course.common.req.CourseAuditViewREQ;
import com.roncoo.education.course.common.resq.CourseAuditGetRESQ;
import com.roncoo.education.course.common.resq.CourseAuditPageRESQ;
import com.roncoo.education.course.common.resq.CourseAuditViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 */
@RestController
@RequestMapping(value = "/course/pc/course/audit")
public class PcApiCourseAuditController extends BaseController {

	@Autowired
	private PcApiCourseAuditBiz biz;

	/**
	 * 课程-审核分页列出接口
	 *
	 * @param courseAuditPageREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核分页列出接口", notes = "课程-审核分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseAuditPageRESQ>> list(@RequestBody CourseAuditPageREQ courseAuditPageREQ) {
		return biz.list(courseAuditPageREQ);
	}

	/**
	 * 课程-审核更新接口
	 *
	 * @param courseAuditUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核更新接口", notes = "课程-审核更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseAuditUpdateREQ courseAuditUpdateREQ) {
		return biz.update(courseAuditUpdateREQ);
	}

	/**
	 * 课程-审核接口
	 *
	 * @param courseAuditUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核接口", notes = "课程-审核接口")
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public Result<Integer> audit(@RequestBody CourseAuditAuditStatusREQ courseAuditAuditStatusREQ) {
		return biz.audit(courseAuditAuditStatusREQ);
	}

	/**
	 * 课程查看接口(课程修改使用)
	 *
	 * @param courseGetREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核查看接口(课程修改使用)", notes = "课程-审核查看接口(课程修改使用)")
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public Result<CourseAuditGetRESQ> get(@RequestBody CourseAuditGetREQ courseAuditGetREQ) {
		return biz.get(courseAuditGetREQ);
	}

	/**
	 * 课程-审核查看接口
	 *
	 * @param courseUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核查看详情接口", notes = "课程-审核查看详情接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseAuditViewRESQ> view(@RequestBody CourseAuditViewREQ courseAuditViewREQ) {
		return biz.view(courseAuditViewREQ);
	}

}
