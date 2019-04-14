package com.fish.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * blog模块会依赖user-center模块中的FishJwtTokenConverter、SecurityConfig等配置项和Class，
 * 所以这里加了@ComponentScan注解，扫描blog和user两个二级目录
 * @author 大漠穷秋
 */
@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages ={"com.fish.blog","com.fish.user"} )
@EntityScan(basePackages = {"com.fish.blog","com.fish.user"})
@ComponentScan(basePackages = {"com.fish.blog","com.fish.user"})
public class NiceFishBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiceFishBlogApplication.class, args);
    }

}