package com.springboot.controller;

import com.springboot.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello Swagger";
    }

    // 只要我们在请求接口的返回值中存在实体类，其就会被扫描到Swagger中
    @GetMapping("/user")
    public User user(){
        return new User();
    }

    @PostMapping("/hello2")
    @ApiOperation("Hello 接口")   // Operation是放在接口方法上
    public String hello2(@ApiParam("用户名") String username) {
        return "Hello "+username;
    }
}
