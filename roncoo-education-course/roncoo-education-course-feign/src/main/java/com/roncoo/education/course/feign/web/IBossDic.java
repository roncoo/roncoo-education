package com.roncoo.education.course.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.web.BossDic;

/**
 * 数据字典 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossDic extends BossDic {

}
