package com.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/10 21:31
 * @Version: 11
 */
@SpringBootApplication(scanBasePackages = {"com.ucenter","com.servicebase"})
@MapperScan("com.ucenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
