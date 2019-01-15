package com.fish.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * blog模块会依赖user-center模块中的FishJwtTokenConverter、SecurityConfig、FishCorsFilter等类，
 * 所以这里加了@ComponentScan注解，扫描blog和user两个二级模块下的Bean
 * @author 大漠穷秋
 */
@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.fish.blog","com.fish.user"})
public class NiceFishBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiceFishBlogApplication.class, args);
    }

}