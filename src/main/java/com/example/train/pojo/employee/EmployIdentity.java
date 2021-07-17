package com.example.train.pojo.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户的身份证号与身份证照片位置的对应关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployIdentity {

    private Integer id;
    private Integer empId;
    private String idNumber;
    private String path;
}
