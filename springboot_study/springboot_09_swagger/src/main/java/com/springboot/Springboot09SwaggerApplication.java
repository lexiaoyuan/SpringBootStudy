package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2  // 开启Swagger2
public class Springboot09SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot09SwaggerApplication.class, args);
    }

}
