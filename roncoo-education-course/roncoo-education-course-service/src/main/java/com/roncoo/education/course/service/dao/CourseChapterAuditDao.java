package com.roncoo.education.course.service.dao;

import java.util.List;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAuditExample;
import com.roncoo.education.util.base.Page;

public interface CourseChapterAuditDao {
	int save(CourseChapterAudit record);

	int deleteById(Long id);

	int updateById(CourseChapterAudit record);

	CourseChapterAudit getById(Long id);

	Page<CourseChapterAudit> listForPage(int pageCurrent, int pageSize, CourseChapterAuditExample example);

	List<CourseChapterAudit> listByCourseId(Long courseId);

	/**
	 * 根据章节编号更新章节排序
	 * 
	 * @param sort
	 * @param chapterId
	 * @return
	 * @author WY
	 */
	int updateSortByChapterId(Integer sort, Long chapterId);

	/**
	 * 根据章节编号更新审核状态
	 * 
	 * @param auditStatus
	 * @param chapterId
	 * @return
	 * @author WY
	 */
	int updateAuditStatusByChapterNo(Integer auditStatus, Long chapterId);

	/**
	 * 根据课程编号删除章节信息
	 * 
	 * @param courseId
	 * @return int
	 * @author wuyun
	 */
	int deleteByCourseId(Long courseId);

	/**
	 * 根据课程编号查询可用的章节信息集合
	 * 
	 * @param courseId
	 * @return statusId
	 * @author wuyun
	 */
	List<CourseChapterAudit> listByCourseIdAndStatusId(Long courseId, Integer statusId);
}