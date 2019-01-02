package com.micro.framework.registry;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 服务发现
 * 健康检查
 * Key/Value 存储
 * 多数据中心
 * @author tb
 * @date 2018/12/29 15:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsulApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "helle consul";
    }
}
