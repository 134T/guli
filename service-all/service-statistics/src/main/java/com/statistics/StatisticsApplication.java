package com.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/19 15:25
 * @Version: 11
 */
@SpringBootApplication(scanBasePackages = {"com.statistics","com.servicebase"})
@MapperScan("com.statistics.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
