package com.example.train.dao;

import org.springframework.stereotype.Repository;

@Repository
/**
 * 培训计划中对组织树的处理dao层
 */
public interface ProEmpDao {

    boolean insertProEmp(Integer proId,Integer empId);

}
