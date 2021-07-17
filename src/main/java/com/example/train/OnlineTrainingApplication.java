package com.example.train;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.example.train.dao")
@MapperScan("com.example.train.security.dao")
@SpringBootApplication
@EnableTransactionManagement
public class OnlineTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTrainingApplication.class, args);
    }

}
