/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.app.sba;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class ServerSbaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSbaApplication.class, args);
    }

}
