package com.roncoo.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.BossZoneCourse;

/**
 * 专区课程关联表 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossZoneCourse extends BossZoneCourse {

}
