package com.fish.nicefisheureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NicefishEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NicefishEurekaApplication.class, args);
    }
}

