package com.roncoo.education.system.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.web.BossWebsite;

/**
 * 站点信息 
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossWebsite extends BossWebsite {

}
