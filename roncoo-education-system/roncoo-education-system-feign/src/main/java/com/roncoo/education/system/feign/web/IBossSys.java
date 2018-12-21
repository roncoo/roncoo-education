package com.roncoo.education.system.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.web.BossSys;

/**
 * 系统配置表 
 *
 * @author YZJ
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSys extends BossSys {

}
