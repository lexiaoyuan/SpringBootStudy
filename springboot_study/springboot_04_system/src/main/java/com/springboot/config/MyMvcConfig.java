package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 转到index.html
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        // 转到主页
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    // 往容器中注入自定义的组件
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/index.html", "/user/login","/css/**", "/js/**", "/img/**");
    }
}
