package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication : 标注这个类是一个Spring Boot的应用，会导入启动类需要的所有资源
@SpringBootApplication
public class Springboot01Hello2Application {

    public static void main(String[] args) {
        // 将Spring Boot的应用启动
        SpringApplication.run(Springboot01Hello2Application.class, args);
    }

}
