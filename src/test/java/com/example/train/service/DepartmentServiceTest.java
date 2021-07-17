package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.depart.Depart;
import com.example.train.pojo.depart.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @Test
    void findById() {
        List<Department> departments = departmentService.findById("1");
        for (Department department : departments) {
            System.out.println(department);
        }
    }

    @Test
    void findBySuperId(){
        List<Department> departments = departmentService.findBySuperId("0");
        for (Department department : departments) {
            System.out.println(department);
        }
    }

    @Test
    void findAll(){
        List<Depart> departments = departmentService.findAll();
        for (Depart d : departments) {
            System.out.println(d);
        }
    }

    @Test
    void update(){
        Depart depart = new Depart(1,0,"总部");
        System.out.println(departmentService.updateDep(2,8));

    }

    @Test
    void updateDepart(){
        Depart depart = new Depart(6,4,"班组四");
        System.out.println(departmentService.updateDepart(depart));
    }

    @Test
    void deleteDepartById(){
        System.out.println(departmentService.deleteDepartById(10));
    }

//    @Test
//    void deleteDepartBySuperId(){
//        System.out.println(departmentService.deleteDepartBySuperId(9));
//    }

    @Test
    void findAllSubId(){

        List<Integer> integers = departmentService.findAllIdSubId(1);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

}