package com.servicebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/7 22:25
 * @Version: 11
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).groupName("webApi")
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(Predicate.not(PathSelectors.regex("/admin/")))
//                .paths(Predicate.not(PathSelectors.regex("/error/")))
                .paths(PathSelectors.regex("/error").negate())
                .paths(PathSelectors.regex("/admin").negate())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .contact(new Contact("Java", "http://atguigu.com", "1830869070@qq.com"))
                .version("1.0")
                .build();
    }
}
