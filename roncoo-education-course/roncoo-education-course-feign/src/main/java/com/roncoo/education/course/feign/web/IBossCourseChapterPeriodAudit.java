package com.roncoo.education.course.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.web.BossCourseChapterPeriodAudit;

/**
 * 课时信息-审核 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseChapterPeriodAudit extends BossCourseChapterPeriodAudit {

}
