package com.roncoo.education.course.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.web.BossCourseIntroduceAudit;

/**
 * 课程介绍信息 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseIntroduceAudit extends BossCourseIntroduceAudit {

}
