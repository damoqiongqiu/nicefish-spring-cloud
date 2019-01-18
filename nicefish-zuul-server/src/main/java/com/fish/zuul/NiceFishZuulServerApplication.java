package com.fish.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrixDashboard
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.fish.zuul","com.fish.user"})
public class NiceFishZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiceFishZuulServerApplication.class, args);
    }
}

