package com.micro.framework.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author tb
 * @date 2018/12/27 16:01
 */
@SpringBootApplication
@EnableEurekaServer
public class MicroRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroRegistryApplication.class);
    }
}
