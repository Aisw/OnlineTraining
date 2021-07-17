package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findById() {
        System.out.println(userService.findById(1));
    }
}