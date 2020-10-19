package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg", "using thymeleaf");
        return "test";  // templates/test.html
    }

    @GetMapping("/test2")
    public String test2(Map<String, Object> map) {
        map.put("msg","<h1>Hello</h1>" );
        map.put("users", Arrays.asList("lexiaoyuan", "lexiaoyuanbeta"));
        return "test";
    }
}
