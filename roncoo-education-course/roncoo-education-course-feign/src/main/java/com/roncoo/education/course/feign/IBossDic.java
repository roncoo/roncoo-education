package com.roncoo.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.course.common.interfaces.BossDic;

/**
 * 数据字典 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossDic extends BossDic {

}
