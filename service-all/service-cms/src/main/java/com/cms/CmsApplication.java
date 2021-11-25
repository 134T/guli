package com.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/9 15:15
 * @Version: 11
 */
@SpringBootApplication(scanBasePackages = {"com.cms","com.servicebase"})
@MapperScan("com.cms.mapper")
@EnableDiscoveryClient
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
