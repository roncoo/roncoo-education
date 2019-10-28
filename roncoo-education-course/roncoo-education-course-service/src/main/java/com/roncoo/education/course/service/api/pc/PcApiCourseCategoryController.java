package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.api.pc.biz.PcApiCourseCategoryBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.req.CourseCategoryDeleteREQ;
import com.roncoo.education.course.common.req.CourseCategoryPageREQ;
import com.roncoo.education.course.common.req.CourseCategorySaveREQ;
import com.roncoo.education.course.common.req.CourseCategoryUpdateREQ;
import com.roncoo.education.course.common.req.CourseCategoryViewREQ;
import com.roncoo.education.course.common.resq.CourseCategoryPageRESQ;
import com.roncoo.education.course.common.resq.CourseCategoryViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程分类
 *
 */
@RestController
@RequestMapping(value = "/course/pc/course/category")
public class PcApiCourseCategoryController extends BaseController {

	@Autowired
	private PcApiCourseCategoryBiz biz;

	/**
	 * 分页列出课程分类信息
	 *
	 * @param courseCategoryPageREQ
	 * @return
	 */
	@ApiOperation(value = "分页列出课程分类信息", notes = "分页列出课程分类信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseCategoryPageRESQ>> listForPage(@RequestBody CourseCategoryPageREQ courseCategoryPageREQ) {
		return biz.listForPage(courseCategoryPageREQ);
	}

	/**
	 * 添加课程分类信息
	 *
	 * @param courseCategorySaveREQ
	 * @return
	 */
	@ApiOperation(value = "添加课程分类信息", notes = "添加课程分类信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CourseCategorySaveREQ courseCategorySaveREQ) {
		return biz.save(courseCategorySaveREQ);
	}

	/**
	 * 删除课程分类信息
	 *
	 * @param courseCategoryDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "删除课程分类信息", notes = "删除课程分类信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody CourseCategoryDeleteREQ courseCategoryDeleteREQ) {
		return biz.delete(courseCategoryDeleteREQ);
	}

	/**
	 * 更新课程分类信息
	 *
	 * @param courseCategoryUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "更新课程分类信息", notes = "更新课程分类信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseCategoryUpdateREQ courseCategoryUpdateREQ) {
		return biz.update(courseCategoryUpdateREQ);
	}

	/**
	 * 查看课程分类信息
	 *
	 * @param courseCategoryViewREQ
	 * @return
	 */
	@ApiOperation(value = "查看课程分类信息", notes = "查看课程分类信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseCategoryViewRESQ> view(@RequestBody CourseCategoryViewREQ courseCategoryViewREQ) {
		return biz.view(courseCategoryViewREQ);
	}

}
