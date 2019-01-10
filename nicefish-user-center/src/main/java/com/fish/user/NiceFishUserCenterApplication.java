package com.fish.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
/**
 * @author 大漠穷秋
 */
@SpringBootApplication
@EnableResourceServer
public class NiceFishUserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiceFishUserCenterApplication.class, args);
    }
}
