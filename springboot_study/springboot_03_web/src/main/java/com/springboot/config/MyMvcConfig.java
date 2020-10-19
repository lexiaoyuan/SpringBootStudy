package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

// 如果你想自定义一些定制化的功能，只需要写这个组件，然后将它交给Spring Boot，spring boot就会帮我们自动装配
// 扩展Spring MVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    // ViewResolver 实现了视图解析器接口，我们就可以把它看做视图解析器
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    // 自定义了一个自己的视图解析器MyViewResolver
    public static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
           return null;
        }
    }
}
