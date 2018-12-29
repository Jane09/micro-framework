package com.micro.framework.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tb
 * @date 2018/12/29 14:59
 */
@SpringBootApplication
public class EtcdApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtcdApplication.class);
    }
}
