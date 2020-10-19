package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    // 开启Swagger的bean实例
    @Bean
    public Docket docket(Environment environment){

        // 设置显示Swagger的环境
        Profiles profiles = Profiles.of("dev", "test");
        // 通过environment.acceptsProfiles判断是否在自己的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)// 是否开启Swagger
                .groupName("乐小猿")
                .select()
                // RequestHandlerSelectors：配置扫描接口的方式
                // basePackage:指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.springboot.controller"))
                // paths():过滤什么路径
                .paths(PathSelectors.ant("/hello/**"))  //只有/hello/路径下的才能扫描到
                .build();
    }

    // 配置多个分组
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("乐小猿", "http://www.lexiaoyuan.club", "2775886918@foxmail.com");

        // Api信息
        return new ApiInfo(
                "乐小猿的SwaggerAPI文档"
                , "这是学习使用Swagger的文档"
                , "v1.0"
                , "http://www.lexiaoyuan.club"
                , contact
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList<>());
    }


}
