package com.roncoo.education.user.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.LecturerAuditQO;
import com.roncoo.education.user.common.bean.vo.LecturerAuditVO;
import com.roncoo.education.util.base.Page;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
public interface BossLecturerAudit {

	@RequestMapping(value = "/boss/user/lecturerAudit/listForPage", method = RequestMethod.POST)
	Page<LecturerAuditVO> listForPage(@RequestBody LecturerAuditQO qo);

	@RequestMapping(value = "/boss/user/lecturerAudit/save", method = RequestMethod.POST)
	int save(@RequestBody LecturerAuditQO qo);

	@RequestMapping(value = "/boss/user/lecturerAudit/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/lecturerAudit/update", method = RequestMethod.PUT)
	int updateById(@RequestBody LecturerAuditQO qo);

	@RequestMapping(value = "/boss/user/lecturerAudit/get/{id}", method = RequestMethod.GET)
	LecturerAuditVO getById(@PathVariable(value = "id") Long id);

	/**
	 * 审核
	 * 
	 * @param lecturerAccoutQO
	 * @return
	 */
	@RequestMapping(value = "/boss/user/lecturerAudit/audit", method = RequestMethod.POST)
	int audit(@RequestBody LecturerAuditQO qo);

	/**
	 * 讲师添加时候的手机号码校验
	 * 
	 * @param LecturerAuditQO
	 * @return
	 */
	@RequestMapping(value = "/boss/user/lecturerAudit/checkUserAndLecturer", method = RequestMethod.POST)
	LecturerAuditVO checkUserAndLecturer(LecturerAuditQO qo);
}
