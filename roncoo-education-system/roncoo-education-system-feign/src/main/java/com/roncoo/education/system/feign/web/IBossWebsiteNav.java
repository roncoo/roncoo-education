package com.roncoo.education.system.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.web.BossWebsiteNav;

/**
 * 站点导航 
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossWebsiteNav extends BossWebsiteNav {

}
