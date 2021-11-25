package com.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/7 17:54
 * @Version: 11
 */
@SpringBootApplication(scanBasePackages = {"com.generator","com.servicebase"})
@MapperScan("com.generator.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);

    }
}
