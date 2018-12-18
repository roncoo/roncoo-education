package com.roncoo.education.course.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.web.BossZone;

/**
 * 专区 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossZone extends BossZone {

}
