package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class ResDocServiceTest {

    @Autowired
    private ResDocService resDocService;

    @Test
    void addResDoc() {
        System.out.println(resDocService.addResDoc(1, 2));
    }

    @Test
    void  deleteResDoc(){
        System.out.println(resDocService.deleteResDoc(1));
    }

    @Test
    void findDocIdsByResId(){
        List<Integer> list = resDocService.findDocIdsByResId(162);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}