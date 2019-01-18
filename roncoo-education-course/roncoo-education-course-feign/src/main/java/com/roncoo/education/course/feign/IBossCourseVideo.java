package com.roncoo.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.BossCourseVideo;

/**
 * 课程视频信息 
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseVideo extends BossCourseVideo {

}
