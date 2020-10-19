package com.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @GetMapping({"/", "/index"})
    public String hello(Model model) {
        model.addAttribute("msg","Hello Shiro");
        return "index";
    }

    @GetMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @GetMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        // 1. 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 2. 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        // 3. 身份认证
        try {
            currentUser.login(token);   // 执行登录方法，如果没有异常说明OK了
            return "index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }

    }

    @GetMapping("/unauthorized")
    @ResponseBody
    public String unauthorized() {
        return "没有权限访问该页面";
    }
}
