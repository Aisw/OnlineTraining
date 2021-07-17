package com.example.train.pojo.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * employee的简化版
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employ {

    private String name;
    private String sex;
    private String isAdministrator;

}
