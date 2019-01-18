package com.roncoo.education.course.common.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseCategoryQO;
import com.roncoo.education.course.common.bean.vo.CourseCategoryVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程分类
 *
 * @author wujing
 */
public interface BossCourseCategory {

	@RequestMapping(value = "/boss/course/courseCategory/listForPage", method = RequestMethod.POST)
	Page<CourseCategoryVO> listForPage(@RequestBody CourseCategoryQO qo);

	@RequestMapping(value = "/boss/course/courseCategory/save", method = RequestMethod.POST)
	int save(@RequestBody CourseCategoryQO qo);

	@RequestMapping(value = "/boss/course/courseCategory/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/courseCategory/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseCategoryQO qo);

	@RequestMapping(value = "/boss/course/courseCategory/get/{id}", method = RequestMethod.GET)
	CourseCategoryVO getById(@PathVariable(value = "id") Long id);

	/**
	 * 根据ID列出分类信息
	 * 
	 * @author WY
	 */
	@RequestMapping(value = "/boss/course/courseCategory/listByParentId/{parentId}", method = RequestMethod.GET)
	List<CourseCategoryVO> listByParentId(@PathVariable(value = "parentId") Long parentId);

	/**
	 * 根据层级列出分类信息
	 * 
	 * @author WY
	 */
	@RequestMapping(value = "/boss/course/courseCategory/listByFloor/{floor}", method = RequestMethod.GET)
	List<CourseCategoryVO> listByFloor(@PathVariable(value = "floor") Integer floor);

	/**
	 * 根据层级、父分类ID列出分类信息
	 * 
	 * @author WY
	 */
	@RequestMapping(value = "/boss/course/courseCategory/listByFloorAndCategoryId", method = RequestMethod.POST)
	List<CourseCategoryVO> listByFloorAndCategoryId(@RequestBody CourseCategoryQO qo);
}
