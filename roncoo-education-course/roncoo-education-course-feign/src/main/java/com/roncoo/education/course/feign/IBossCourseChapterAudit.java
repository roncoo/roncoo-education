package com.roncoo.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.BossCourseChapterAudit;

/**
 * 章节信息-审核 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseChapterAudit extends BossCourseChapterAudit {

}
