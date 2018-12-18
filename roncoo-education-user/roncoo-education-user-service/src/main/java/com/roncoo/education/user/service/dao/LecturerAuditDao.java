package com.roncoo.education.user.service.dao;


import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerAudit;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerAuditExample;
import com.roncoo.education.util.base.Page;

public interface LecturerAuditDao {
	int save(LecturerAudit record);

	int deleteById(Long id);

	int updateById(LecturerAudit record);

	LecturerAudit getById(Long id);

	Page<LecturerAudit> listForPage(int pageCurrent, int pageSize, LecturerAuditExample example);

    /**
	 * 根据讲师编号查询讲师账户信息
	 * 
	 * @param orgNo
	 * @param lecturerUserNo
	 * @return
	 * @author LHR
	 */
	LecturerAudit getByLecturerUserNo(Long lecturerUserNo);

}