package com.example.train.pojo.employee;

import com.example.train.dao.DepartmentDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * 用户详细信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;
    private String password;
    private Integer departId;
    private String name;
    private String idNumber;
    private String sex;
    private Date birthday;
    private String unit;
    private String station;
    private String firstDegree;
    private String secondDegree;
    private String phone;
    private String isAdministrator;

}
