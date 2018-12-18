package com.roncoo.education.system.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.web.BossWebsiteLink;

/**
 * 站点友情链接 
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossWebsiteLink extends BossWebsiteLink {

}
