package com.springboot.mapper;

import com.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<User> queryUsers();

    User queryUserById(@Param("id") int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("id") int id);

}
