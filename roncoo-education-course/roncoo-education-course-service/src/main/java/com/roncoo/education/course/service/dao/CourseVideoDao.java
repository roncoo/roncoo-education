package com.roncoo.education.course.service.dao;

import java.util.List;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseVideoExample;
import com.roncoo.education.util.base.Page;

public interface CourseVideoDao {
	int save(CourseVideo record);

	int deleteById(Long id);

	int updateById(CourseVideo record);

	CourseVideo getById(Long id);

	Page<CourseVideo> listForPage(int pageCurrent, int pageSize, CourseVideoExample example);

	/**
	 * 根据视频编号、课时ID查询课程视频信息
	 * 
	 * @param videoNo
	 * @param periodId
	 * @return
	 * @author wuyun
	 */
	CourseVideo getByVideoNoAndPeriodId(Long videoNo, Long periodId);

	/**
	 * 根据视频编号查询课程视频信息集合
	 * 
	 * @param videoNo
	 * @return
	 * @author wuyun
	 */
	List<CourseVideo> listByVideoNo(Long videoNo);

	/**
	 * 根据课时ID查询可用的课程视频信息集合
	 * 
	 * @param periodId
	 * @param statusId
	 * @return
	 * @author wuyun
	 */
	List<CourseVideo> listByPeriodIdAndStatusId(Long periodId, Integer statusId);

	/**
	 * 根据视频编号查询可用的课程视频信息
	 * 
	 * @param videoNo
	 * @param statusId
	 * @return
	 * @author wuyun
	 */
	CourseVideo getByVideoNoAndStatusId(Long videoNo, Integer statusId);

	/**
	 * 根据视频编号、课时ID查询可用的课程视频信息集合
	 * 
	 * @param videoNo
	 * @param periodId
	 * @param statusId
	 * @return
	 * @author wuyun
	 */
	List<CourseVideo> listByVideoNoAndNotEqualPeriodIdAndStatusId(Long videoNo, Long periodId, Integer statusId);

	/**
	 * 根据视频编号课程视频信息
	 * 
	 * @param videoNo
	 * @return
	 * @author wuyun
	 */
	CourseVideo getByVideoNo(Long videoNo);

	/**
	 * 根据章节ID、课时ID查询可用的课程视频信息集合
	 * 
	 * @param chapterId
	 * @param periodId
	 * @param statusId
	 * @return
	 * @author wuyun
	 */
	List<CourseVideo> listByChapterIdAndPeriodIdAndStatusId(Long chapterId, Long periodId, Integer statusId);
}