package com.example.train.dao;

import com.example.train.pojo.depart.DepartDes;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDesDao {

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    DepartDes selectById(String id);

    /**
     * 根据id修改部门信息
     * @param departDes
     * @return
     */
    boolean updateDepartDes(DepartDes departDes);

    /**
     * 根据id删除org_des的组织
     * @param id
     * @return
     */
    Integer deleteDepartDesById(Integer id);
}
