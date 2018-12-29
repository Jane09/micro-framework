package com.micro.framework.registry;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author tb
 * @date 2018/12/29 15:00
 */
@SpringBootApplication
public class ConsulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsulApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
