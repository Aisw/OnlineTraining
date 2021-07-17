package com.example.train.service;

import com.example.train.dao.DepartmentDao;
import com.example.train.pojo.depart.Depart;
import com.example.train.pojo.depart.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;


    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public List<Department> findById(String id) {
        return departmentDao.selectById(id);
    }

    public List<Department> findBySuperId(String id) {
        return departmentDao.selectById(id);
    }

    /**
     * 查询所有简化的department信息
     * @return
     */
    public List<Depart> findAll() {
        return departmentDao.selectAll();
    }

    /**
     * 根据id查询简化的department
     * @param id
     * @return
     */
    public Depart findDepartById(Integer id){
        return departmentDao.selectDepartById(id);
    }

    /**
     * 在组织机构管理移动时修改上下级节点
     * @param parent
     * @param son
     * @return
     */
    public boolean updateDep(Integer parent, Integer son) {
        return departmentDao.updateDep(parent, son);
    }

    /**
     * 修改organization的名称
     * @param depart
     * @return
     */
    public boolean updateDepart(Depart depart){
        return departmentDao.updateDepart(depart);
    }

    /**
     * 根据id删除organization组织项
     * @param id
     * @return
     */
    public Integer deleteDepartById(Integer id){
        return departmentDao.deleteDepartById(id);
    }

//    /**
//     * 删除superId的所有下级节点的集合
//     * @param superId
//     * @return
//     */
//    public Integer deleteDepartBySuperId(Integer superId){
//        return departmentDao.deleteDepartBySuperId(superId);
//    }

    /**
     * 查询节点superId的下级节点的所有id
     * @param superId
     * @return
     */
    public List<Integer> findAllIdSubId(Integer superId){

        List<Integer> integerList = new ArrayList<>();

        List<Integer> integers = departmentDao.selectIdBySuperId(superId);
        integerList.addAll(integers);

        while (integers.size()!=0){
            Integer id = integers.get(0);
            List<Integer> tempList = departmentDao.selectIdBySuperId(id);
            integerList.addAll(tempList);

            integers.remove(0);
            integers.addAll(tempList);

        }

        Collections.sort(integerList);

        return integerList;
    }
}
