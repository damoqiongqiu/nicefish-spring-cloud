package com.fish.swagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Swagger2 文档总入口依赖所有其它Module，配置ComponentScan扫描范围为一级目录com.fish
 * @author 大漠穷秋
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.fish"})
public class NiceFishSwagger2ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(NiceFishSwagger2ApiApplication.class, args);
    }

}

