/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.roncoo.shiro.freemarker.ShiroTags;

/**
 * shiro配置类
 */
@Configuration
public class ComponentShiro {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@PostConstruct
	public void setSharedVariable() {
		freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
}
