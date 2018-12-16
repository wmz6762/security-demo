package com.example.security;

import com.example.security.properties.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class}) //注入配置文件
public class SecurityExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityExampleApplication.class, args);
    }

}

