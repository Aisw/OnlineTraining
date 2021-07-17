package com.example.train.service;

import com.example.train.dao.ProEmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 培训计划添加组织树下属职工
 */
public class ProEmpService {

    @Autowired
    private ProEmpDao proEmpDao;


    /**
     * 培训计划中添加培训人员
     * @param proId
     * @param empId
     * @return
     */
    public boolean programAddEmployees(Integer proId,Integer empId){
        return proEmpDao.insertProEmp(proId,empId);
    }
}
