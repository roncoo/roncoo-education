package com.roncoo.education.course.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.web.BossCourseAudit;

/**
 * 课程信息-审核 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseAudit extends BossCourseAudit {

}
