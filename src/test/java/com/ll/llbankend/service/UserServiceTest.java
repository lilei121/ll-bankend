package com.ll.llbankend.service;

import java.util.Date;

import com.ll.llbankend.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest()
class UserServiceTest {
    @Resource
    UserService userService;

    @Test
    void addUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("lilei");
        user.setUserAccount("123");
        user.setUserPassword("123");
        user.setGender(0);
        user.setAvatarUrl("");
        user.setPhone("");
        user.setEmail("");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUserRole(0);
        user.setIsDelete(0);
        boolean save = userService.save(user);
        Assertions.assertEquals("lilei", user.getUsername());
        System.out.println(user.getId());

    }

    @Test
    void userRegister() {
        String username = "li lei";
        String userpassword = "123456";
        String checkpassword = "123456";
        long id = userService.userRegister(username, userpassword, checkpassword);
        System.out.println(id);
    }
}