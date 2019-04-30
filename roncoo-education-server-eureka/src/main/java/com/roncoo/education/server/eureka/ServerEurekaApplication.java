/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * 
 * @author wujing
 */
@EnableEurekaServer
@SpringBootApplication
public class ServerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerEurekaApplication.class, args);
        System.out.println("注册中心(server-eureka)启动成功，请接着启动配置中心(server-config)");
    }
    
}
