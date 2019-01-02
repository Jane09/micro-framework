package com.micro.framework.registry;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tb
 * @date 2019/1/2 10:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsulClientApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }
}
