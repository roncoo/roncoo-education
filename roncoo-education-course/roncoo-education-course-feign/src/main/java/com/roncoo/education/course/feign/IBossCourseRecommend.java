package com.roncoo.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.BossCourseRecommend;

/**
 * 课程推荐
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseRecommend extends BossCourseRecommend {

}
