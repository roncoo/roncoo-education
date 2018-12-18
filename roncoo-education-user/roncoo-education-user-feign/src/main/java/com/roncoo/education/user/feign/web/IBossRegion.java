package com.roncoo.education.user.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.web.BossRegion;

/**
 * 行政区域表 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossRegion extends BossRegion {

}
