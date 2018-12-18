package com.roncoo.education.system.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.web.BossNavBar;

/**
 * 头部导航 
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossNavBar extends BossNavBar {

}
