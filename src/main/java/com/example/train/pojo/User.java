package com.example.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String account;
    private String sex;
    private Date birthday;
    private String department;
    private String firstDegree;
    private String secondDegree;
    private String telePhone;
    private String qq;

}
