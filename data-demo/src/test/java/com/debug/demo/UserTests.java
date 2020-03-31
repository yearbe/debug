package com.debug.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.debug.demo.entity.User;
import com.debug.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Lyb
 * @since 2020-01-19
 */
@SpringBootTest
class UserTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void addUser() {
        User user = new User();
        user.setName("李小明");
        user.setAge(22);
        user.setSex(0);
        user.setIdCard("440982194910103355");
        user.setAddress("广东省江门市蓬江区篁庄大道火炬园");
        userMapper.insert(user);
    }

    @Test
    void listUser() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
