package com.springboot.service;

import com.springboot.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryUsers();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
