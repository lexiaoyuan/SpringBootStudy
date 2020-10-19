package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    // List中的一个Map对应表中的一行数据
    // Map中的key对应字段名，value对应字段值
    public List<Map<String, Object>> getAll() {
        String sql = "select * from user;";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList;
    }

    @GetMapping("/add")
    public String addUser() {
        String sql = "insert into user (id, name, pwd) values (4, 'Spring boot', 'boot');";
        jdbcTemplate.update(sql);
        return "add ok";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id) {
        String sql = "update  user set name = ? , pwd = ? where id = "+id+";";
        Object[] objects = new Object[2];
        objects[0] = "beta";
        objects[1] = "beta2";
        jdbcTemplate.update(sql, objects);
        return "update ok";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql, id);
        return "delete ok";
    }
}
