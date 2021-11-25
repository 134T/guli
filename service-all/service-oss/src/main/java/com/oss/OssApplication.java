package com.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/15 7:01
 * @Version: 11
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.oss","com.servicebase"})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}


