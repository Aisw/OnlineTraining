package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class ProEmpServiceTest {

    @Autowired
    private ProEmpService proEmpService;

    @Test
    void programAddEmployees() {
        System.out.println(proEmpService.programAddEmployees(1, 1));
    }
}