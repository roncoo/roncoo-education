/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education;

import com.roncoo.education.gateway.common.FilterPre;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 服务网关
 * 
 * @author wujing
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApiApplication.class, args);
	}

	@Bean
	public FilterPre filterPre() {
		return new FilterPre();
	}

}
