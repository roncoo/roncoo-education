package com.roncoo.education.user.common.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.LecturerQO;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.util.base.Page;

/**
 * 讲师信息
 *
 * @author wujing
 */
public interface BossLecturer {

	@RequestMapping(value = "/boss/user/lecturer/listForPage", method = RequestMethod.POST)
	Page<LecturerVO> listForPage(@RequestBody LecturerQO qo);

	@RequestMapping(value = "/boss/user/lecturer/save", method = RequestMethod.POST)
	int save(@RequestBody LecturerQO qo);

	@RequestMapping(value = "/boss/user/lecturer/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/lecturer/update", method = RequestMethod.PUT)
	int updateById(@RequestBody LecturerQO qo);

	@RequestMapping(value = "/boss/user/lecturer/get/{id}", method = RequestMethod.GET)
	LecturerVO getById(@PathVariable(value = "id") Long id);

	/***
	 * 根据讲师用户编号查找讲师信息
	 */
	@RequestMapping(value = "/boss/user/lecturer/getByLecturerUserNo/{lecturerUserNo}", method = RequestMethod.GET)
	LecturerVO getByLecturerUserNo(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo);

	/**
	 * 列出所有讲师信息
	 * 
	 * @author LHR
	 */
	@RequestMapping(value = "/boss/user/lecturer/listAllForLecturer", method = RequestMethod.POST)
	List<LecturerVO> listAllForLecturer();
}
