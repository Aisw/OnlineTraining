package com.example.train.pojo.depart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 组织机构组成部门、班组
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    private Integer id;
    private Integer superId;
    private String label;
    private List<Department> children;

    public Department(Integer id, Integer superId,String label){
        this.id = id;
        this.superId = superId;
        this.label = label;
    }
}
