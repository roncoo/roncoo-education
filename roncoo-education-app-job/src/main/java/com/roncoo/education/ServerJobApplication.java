/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务
 */
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServerJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerJobApplication.class, args);
    }

}
