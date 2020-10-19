package com.springboot.controller;

import com.springboot.pojo.User;
import com.springboot.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/query")
    public List<User> queryUsers() {
        return userService.queryUsers();
    }

    @GetMapping("/query/{id}")
    public User queryUserById(@PathVariable("id") int id) {
        return userService.queryUserById(id);
    }

    @GetMapping("/add")
    public String addUser(User user) {
        user.setId(2020);
        user.setName("添加");
        user.setPwd("password");
        userService.addUser(user);
        return "add ok";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id")int id, User user) {
        user.setId(id);
        user.setName("beta");
        user.setPwd("beta");
        userService.updateUser(user);
        return "update ok";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id")int id) {
        userService.deleteUser(id);
        return "delete ok";
    }

}
