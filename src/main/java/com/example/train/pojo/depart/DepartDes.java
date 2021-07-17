package com.example.train.pojo.depart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对于部门信息的详细介绍
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartDes {

    private Integer id;
    private Integer code;
    private String name;
    private String type;
    private String description;
    private String phone;
    private String address;
    private String fix;
    private String people;

}
