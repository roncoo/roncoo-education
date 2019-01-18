package com.roncoo.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.BossZone;

/**
 * 专区 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossZone extends BossZone {

}
