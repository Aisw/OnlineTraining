package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 培训计划中绑定组织树关系模型
 */
public class ProEmp {

    private Integer id;
    private Integer proId;
    private Integer empId;

}
