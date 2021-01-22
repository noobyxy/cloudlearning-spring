package com.yxy.cl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    //配置了Swagger中的Docket的Bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any全部，none不扫描
                //withClassAnnotation：扫描类上的注解
                //withMethodAnnotation：扫描方法上的注解
                .select().apis(RequestHandlerSelectors.basePackage("com.yxy.cl.controller"))
                .build();
    }

    //配置Swagger信息apiInfo
    private ApiInfo apiInfo() {
        return new ApiInfo("CloudLearning的api文档",
                "Api Documentation",
                "1.0",
                "urn:tos",
                new Contact("杨新宇","cloudlearning.xyz","3291204416@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    //Swagger配置扫描接口

}
