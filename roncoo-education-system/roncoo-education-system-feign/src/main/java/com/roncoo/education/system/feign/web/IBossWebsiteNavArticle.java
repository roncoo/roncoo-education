package com.roncoo.education.system.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.web.BossWebsiteNavArticle;

/**
 * 站点导航文章 
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossWebsiteNavArticle extends BossWebsiteNavArticle {

}
