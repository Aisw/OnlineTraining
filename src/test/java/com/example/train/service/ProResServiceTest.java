package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class ProResServiceTest {

    @Autowired
    private ProResService proResService;

    @Test
    void addProRes(){
        System.out.println(proResService.addProRes(1, 3));
        System.out.println(proResService.addProRes(1, 4));
    }

    @Test
    void getResIdsByProId(){
        for (Integer integer : proResService.getResIdsByProId(156)) {
            System.out.println(integer);
        }
    }
}