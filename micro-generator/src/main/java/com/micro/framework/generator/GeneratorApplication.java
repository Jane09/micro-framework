package com.micro.framework.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author tb
 * @date 2019/1/2 11:49
 */
@SpringBootApplication
@MapperScan("com.micro.framework.generator.mapper")
public class GeneratorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GeneratorApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
