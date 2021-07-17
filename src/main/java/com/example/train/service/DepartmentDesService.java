package com.example.train.service;

import com.example.train.dao.DepartmentDesDao;
import com.example.train.pojo.depart.DepartDes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentDesService {

    @Autowired
    private DepartmentDesDao departmentDesDao;

    /**
     * 根据id查询org_des组织项
     * @param id
     * @return
     */
    public DepartDes findById(String id) {
        return departmentDesDao.selectById(id);
    }


    /**
     * 修改org_des组织项的详细信息
     * @param departDes
     * @return
     */
    public boolean modifyDepartDes(DepartDes departDes) {
        return departmentDesDao.updateDepartDes(departDes);
    }

    /**
     * 根据id删除org_des的组织项
     * @param id
     * @return
     */
    public Integer deleteDepartDesById(Integer id){
        return departmentDesDao.deleteDepartDesById(id);
    }
}
