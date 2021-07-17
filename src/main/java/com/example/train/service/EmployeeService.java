package com.example.train.service;

import com.example.train.dao.EmployeeDao;
import com.example.train.pojo.employee.Employ;
import com.example.train.pojo.employee.EmployIdentity;
import com.example.train.pojo.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    public Employee findById(Integer id){
        return employeeDao.selectById(id);
    }

    /**
     * 根据部门id查询该部门下员工列表
     * @param departId
     * @return
     */
    public List<Employee> findByDepartId(Integer departId){
        return employeeDao.selectByDepartId(departId);
    }

    /**
     * 根据员工name、sex、isAdministrator查询员工信息
     * @param employ
     * @return
     */
    public List<Employee> findByEmploy(Employ employ){
        return  employeeDao.selectByEmploy(employ);
    }

    /**
     * 获取employee表的id
     * @return
     */
//    public boolean addEmp(){
//        return employeeDao.insertEmp();
//    }
    /**
     * 新增Employee
     * @param employee
     * @return
     */
    public Integer addEmployee(Employee employee){
        return employeeDao.insertEmployee(employee);
    }

    /**
     *
     * 修改employee用户信息
     * @param employee
     * @return
     */
    public boolean modifyEmployee(Employee employee){
        return employeeDao.updateEmployee(employee);
    }

    /**
     * 根据id删除用户employee
     * @param id
     * @return
     */
    public boolean deleteEmployeeById(Integer id){
        return employeeDao.deleteEmployeeById(id);
    }

    /**
     * 根据身份证号插入身份证照片地址
     * @param employIdentity
     * @return
     */
    public boolean uploadIdentityphoto(EmployIdentity employIdentity){
        return employeeDao.uploadIdentityphoto(employIdentity);
    }

    /**
     * 根据用户id查询上传照片
     * @param empId
     * @return
     */
    public List<String> findPathByEmpId(Integer empId){
        return employeeDao.selectPathByEmpId(empId);
    }

    /**
     * 通过empId清除用户在emp_ide中的数据
     * @param empId
     * @return
     */
    public boolean deleteEmpIdebyEmpId(Integer empId){
        return employeeDao.deleteEmpIde(empId);
    }
}
