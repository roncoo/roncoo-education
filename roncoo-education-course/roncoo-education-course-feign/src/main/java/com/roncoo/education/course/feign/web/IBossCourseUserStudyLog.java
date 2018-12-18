package com.roncoo.education.course.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.web.BossCourseUserStudyLog;

/**
 * 课程用户学习日志 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseUserStudyLog extends BossCourseUserStudyLog {

}
