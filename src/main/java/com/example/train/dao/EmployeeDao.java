package com.example.train.dao;

import com.example.train.pojo.employee.Employ;
import com.example.train.pojo.employee.EmployIdentity;
import com.example.train.pojo.employee.Employee;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    /**
     * 根据部门id查询该部门下的员工
     * @param departId
     * @return
     */
    List<Employee> selectByDepartId(Integer departId);

    /**
     * 根据用户id查询员工详细信息
     * @param id
     * @return
     */
    Employee selectById(Integer id);

    /**
     * 根据用户名称、性别、是否管理员查询用户详细信息
     * @param employ
     * @return
     */
    List<Employee> selectByEmploy(Employ employ);

//    boolean insertEmp(Employee employee);

    /**
     * employee数据插入
     * @param employee
     * @return
     */
    Integer insertEmployee(Employee employee);

    /**
     * 修改Employee信息
     * @param employee
     * @return
     */
    boolean updateEmployee(Employee employee);

    /**
     * 根据用户id删除用户employee
     * @param id
     * @return
     */
    boolean deleteEmployeeById(Integer id);

    /**
     * 根据身份证号码插入照片地址
     * @param employIdentity
     * @return
     */
    boolean uploadIdentityphoto(EmployIdentity employIdentity);

    /**
     * 通过用户id获取身份证照片上传地址
     * @param empId
     * @return
     */
    List<String> selectPathByEmpId(Integer empId);

    /**
     * 清空用户在emp_ide的数据
     * @param empId
     * @return
     */
    boolean deleteEmpIde(Integer empId);
}
