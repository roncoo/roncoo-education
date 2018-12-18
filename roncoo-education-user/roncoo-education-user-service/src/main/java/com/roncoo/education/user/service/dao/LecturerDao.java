package com.roncoo.education.user.service.dao;

import java.util.List;

import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExample;
import com.roncoo.education.util.base.Page;

public interface LecturerDao {
	int save(Lecturer record);

	int deleteById(Long id);

	int updateById(Lecturer record);

	Lecturer getById(Long id);

	Page<Lecturer> listForPage(int pageCurrent, int pageSize, LecturerExample example);

	/**
	 * 根据讲师编号查找讲师信息
	 * 
	 * @param lecturerUserNo
	 * @return
	 * @author WY
	 */
	Lecturer getByLecturerUserNo(Long lecturerUserNo);

	/**
	 * 根据讲师名称查找讲师信息
	 * 
	 * @param LecturerName
	 * @return
	 * @author WY
	 */
	List<Lecturer> getByLecturerName(String LecturerName);

	List<Lecturer> listByStatusId(Integer statusId);

	/**
	 * 根据讲师编号、状态查找讲师信息
	 * 
	 * @param lecturerUserNo
	 * @param statusId
	 * @return
	 * @author WY
	 */
	Lecturer getByLecturerUserNoAndStatusId(Long lecturerUserNo, Integer statusId);
}