package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.employee.Employ;
import com.example.train.pojo.employee.EmployIdentity;
import com.example.train.pojo.employee.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void findById() {
        System.out.println(employeeService.findById(1));
    }

    @Test
    void findByDepartId(){
        System.out.println(employeeService.findByDepartId(7));
    }

    @Test
    void findByEmploy(){
        Employ employ = new Employ();
        List<Employee> employees = employeeService.findByEmploy(employ);
        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }

//    @Test
//    void addEmp(){
//
//    }

    @Test
    void addEmployee(){
        Employee employee = new Employee();
        employee.setIsAdministrator("否");
        employeeService.addEmployee(employee);
        System.out.println(employee.getId());
//        employee.setName("小四");
//        System.out.println(employeeService.addEmployee(employee));
    }

    @Test
    void modifyEmployee(){
        Employee employee = new Employee();
        employee.setId(5);
        employee.setName("小五五");
        System.out.println(employeeService.modifyEmployee(employee));
    }

    @Test
    void deleteEmployeeById(){
        System.out.println(employeeService.deleteEmployeeById(4));
    }

    @Test
    void uploadIdentityphoto(){
        EmployIdentity employIdentity = new EmployIdentity();
        employIdentity.setIdNumber("370405199812181336");
        employIdentity.setPath("/qweqe.png");
        System.out.println(employeeService.uploadIdentityphoto(employIdentity));
    }

    @Test
    void findPathByEmpId(){
        List<String> list = employeeService.findPathByEmpId(62);
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    void deleteEmpIdebyEmpId(){
        System.out.println(employeeService.deleteEmpIdebyEmpId(1));
    }
}