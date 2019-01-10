package com.fish.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author 大漠穷秋
 */
@SpringBootApplication
@EnableAuthorizationServer
public class NiceFishOAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(NiceFishOAuthApplication.class, args);
    }
}
